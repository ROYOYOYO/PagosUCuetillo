/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjpa.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.openjpa.control.exceptions.EntidadPreexistenteException;
import org.openjpa.control.exceptions.NoExisteEntidadException;
import org.openjpa.entidades.Alumno;
import org.openjpa.entidades.Carrera;
import org.openjpa.entidades.Pago;
/**
 * FXML Controller class
 *
 * @author AJ
 */
public class PagosController implements Initializable {
   
    private ObservableList<String> opcionesAlumno;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnSalir;
    @FXML
    private TableView tblAlumnos;
    @FXML
    private Spinner<Double> spMonto;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnNuevo;
    @FXML
    private TextField txtId;
    @FXML
    private ComboBox<String> cbMPago;
    @FXML
    private ComboBox<String> cbAlumno;
    private PagoControl pagoControl;
    private AlumnoControl alumnoControl;
    
    public PagosController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseDatos");
        this.pagoControl = new PagoControl(emf);
        opcionesAlumno = FXCollections.observableArrayList();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Start();        
        InicializarTable();
        CargarDatos();
        Nuevo();   
        btnAgregar.setDisable(true);
        DesActivar(true);
    }   
    
    public void Start()
    {       
        SpinnerValueFactory<Double> edadValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 100.0);
        spMonto.setValueFactory(edadValueFactory); 
        btnModificar.setDisable(true);
        
    }
    
    public void InicializarTable()
    {
        
        TableColumn<Pago, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setVisible(false);
        
        TableColumn<Pago, String> nombreColumn = new TableColumn<>("nombre");
        nombreColumn.setCellValueFactory(cellData -> {
        Pago pago = cellData.getValue();
        int alumnoId = pago.getAlumno();
        if (alumnoId != 0) { // Verificar si el ID es válido
            // Obtener la entidad Carrera según el ID
            Alumno alumno = pagoControl.obtenerAlumnoPorId(alumnoId);
            if (alumno != null) {
                return new SimpleStringProperty(alumno.getNombre());
            }
        }
        return new SimpleStringProperty("");
        });
        TableColumn<Pago, Double> montoColumn = new TableColumn<>("Monto");
        montoColumn.setCellValueFactory(new PropertyValueFactory<>("monto"));
        
        TableColumn<Pago, String> metodoColumn = new TableColumn<>("Metodo");
        metodoColumn.setCellValueFactory(new PropertyValueFactory<>("metodoPago"));

        tblAlumnos.getColumns().addAll(idColumn, nombreColumn, montoColumn, metodoColumn);
    }
    
    public void CargarDatos()
    {        
        List<Alumno> alumnos = pagoControl.obtenerAlumnosOrdenadasPorId();
        
        List<String> alumnoDescripciones = new ArrayList<>();
        cbMPago.getItems().addAll("Tarjeta", "Efectivo", "Transferencia");
        for (Alumno alumno : alumnos) {
            alumnoDescripciones.add(alumno.getNombre());
        }


        // Asignar las listas de descripciones a los ComboBox correspondientes
        ObservableList<String> opcionesAlumnos = FXCollections.observableArrayList(alumnoDescripciones);

        cbAlumno.setItems(opcionesAlumnos);
        List<Pago> listaAlumnos = pagoControl.buscaPagos();
        tblAlumnos.getItems().setAll(listaAlumnos);
        
    }
    
    public void RecuperarDatos() 
    {
        DesActivar(false);
        tblAlumnos.getSelectionModel().selectedItemProperty().addListener((ObservableValue obs, Object oldSelection, Object newSelection) -> 
        {
            if (newSelection != null) 
            {
                Pago alumnoSeleccionado =  (Pago) newSelection;
                int id = alumnoSeleccionado.getPagoId();
                Double Monto = alumnoSeleccionado.getMonto();              
                int alumno = alumnoSeleccionado.getAlumno();
                String metodo = alumnoSeleccionado.getMetodoPago();
                txtId.setText(""+id);
                spMonto.getValueFactory().setValue(Monto);
                cbAlumno.getSelectionModel().select(alumno-1);
                cbMPago.setValue(metodo);
            }
        });
        btnAgregar.setDisable(true);
        btnModificar.setDisable(false);
    }
    
    public void RecuperarFields(int op) 
    {
        double monto = (double) spMonto.getValue();
        String alumno = (String) cbAlumno.getSelectionModel().getSelectedItem();
        String metodo = (String) cbMPago.getSelectionModel().getSelectedItem();
        if(op==1)
        {
            AgregarDatos(alumno, monto, metodo);
        }
        else
        {
            if(op==2)
            {
                int Id = Integer.parseInt(txtId.getText());
                ModificarDatos(Id, alumno, monto, metodo);
            }
        }
    }
    
    public void AgregarDatos(String alumno, double monto, String metodoPago) 
    {
        Pago pago = new Pago(pagoControl.obtenerAlumnoId(alumno), monto, metodoPago);
        int exito = 0;
        try {
            exito = pagoControl.insertar(pago);
        } catch (EntidadPreexistenteException ex) {
            Logger.getLogger(PagosController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (exito!=0) 
        {            
            Alert alert = new Alert(AlertType.INFORMATION);
            String[] Mensaje = {"Éxito","Pago agregado","El pago se ha agregado correctamente."};
            Mensaje(alert, Mensaje);
            Nuevo();
            btnAgregar.setDisable(true);
            CargarDatos();
            DesActivar(true);
        } 
        else 
        {
            // Mostrar una alerta de error
            Alert alert = new Alert(AlertType.ERROR);
            String[] Mensaje = {"Error","Error al agregar pago","Ha ocurrido un error al intentar agregar el pago."};
            Mensaje(alert, Mensaje);
        }
    }
    
    public void ModificarDatos(int Id,String alumno, double monto, String metodoPago) 
    {
        Pago pago = new Pago(pagoControl.obtenerAlumnoId(alumno), monto, metodoPago);
        pago.setPagoId(Id);
        int exito = 0;
        try {
            exito = pagoControl.editar(pago);
        } catch (NoExisteEntidadException ex) {
            Logger.getLogger(PagosController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (exito!=0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            String[] Mensaje = {"Éxito","Pago modificado","El pago se ha modificado correctamente."};
            Mensaje(alert, Mensaje);
            Nuevo();
            CargarDatos();
            DesActivar(true);
        }
        else
        {
            // Mostrar una alerta de error
            Alert alert = new Alert(AlertType.ERROR);
            String[] Mensaje = {"Error","Error al modificar pago","Ha ocurrido un error al intentar modificar el pago."};
            Mensaje(alert, Mensaje);
        }     
    }
    
     @FXML
    public void Nuevo() 
    {
        DesActivar(false);
        btnAgregar.setDisable(false);
        txtId.setText("");
        cbAlumno.getSelectionModel().select(0);
        cbMPago.getSelectionModel().select(0);
        spMonto.getValueFactory().setValue(150.0);
        btnModificar.setDisable(true);
    }
    public void DesActivar(boolean a)
    {
        cbAlumno.setDisable(a);
        cbMPago.setDisable(a);
        spMonto.setDisable(a);
    }
    @FXML
    public void exit()
    {
        System.exit(0);
    }
    
    public void Mensaje(Alert alert, String[] Mensaje)
    {
        alert.setTitle(Mensaje[0]);
        alert.setHeaderText(Mensaje[1]);
        alert.setContentText(Mensaje[2]);
        alert.showAndWait();
    }

    @FXML
    private void AgregarDatos(ActionEvent event) {
        RecuperarFields(1);
    }
    
    @FXML
    private void ModifDatos(ActionEvent event) {
        RecuperarFields(2);
    }


}


