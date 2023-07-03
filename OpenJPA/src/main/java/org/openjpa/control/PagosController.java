/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjpa.control;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author AJ
 */
public class PagosController implements Initializable {

    @FXML
    private ComboBox<String> cbAlumno;
    @FXML
    private Button btnAgregar;
    @FXML
    private TableView tblAlumnos;
    @FXML
    private Spinner<Double> spMonto;
    @FXML
    private TextField txtId;
    @FXML
    private ComboBox<String> cbMPago;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnNuevo;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Start();        
//        InicializarTable();
//        CargarDatos();
//        Nuevo();   
//        btnAgregar.setDisable(true);
//        DesActivar(true);
    }   
    
    public void Start()
    {       
        ToggleGroup toggleGroup = new ToggleGroup();    
        SpinnerValueFactory<Double> montoValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0f, 2500.0f);
        spMonto.setValueFactory(montoValueFactory);  
    } 
    }
    /*
    public void InicializarTable()
    {
        TableColumn<cAlumno, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setVisible(false);

        TableColumn<cAlumno, String> AlumnoColumn = new TableColumn<>("Alumno");
        AlumnoColumn.setCellValueFactory(new PropertyValueFactory<>("Alumno"));

        TableColumn<cAlumno, Integer> montoColumn = new TableColumn<>("Monto");
        montoColumn.setCellValueFactory(new PropertyValueFactory<>("Monto"));
        
        TableColumn<cAlumno, String> mpagoColumn = new TableColumn<>("MetodoPago");
        mpagoColumn.setCellValueFactory(new PropertyValueFactory<>("MetodoPago"));


        // Agregar las columnas al TableView
        tblAlumnos.getColumns().addAll(idColumn, AlumnoColumn, montoColumn, mpagoColumn);
    }
    
    public void CargarDatos()
    {        
        cConexion con = new cConexion();
        Map<String, String> alumnos = con.obtenerAlumnos();
        ObservableList<String> opcionesAlumnos = FXCollections.observableArrayList(alumnos.values());
        cbMPago.getItems().addAll("Tarjeta", "Efectivo", "Transferencia");
        cbAlumno.setItems(opcionesAlumnos);

        List<cPagos> pagos = con.obtenerDatosPagos();
        ObservableList<cPagos> data = FXCollections.observableArrayList(pagos);
        tblAlumnos.setItems(data);
    }
    
    @FXML
    public void RecuperarDatos() 
    {
        DesActivar(false);
        tblAlumnos.getSelectionModel().selectedItemProperty().addListener((ObservableValue obs, Object oldSelection, Object newSelection) -> 
        {
            if (newSelection != null) 
            {
                cPagos pagoseleccionado =  (cPagos) newSelection;
                String id = pagoseleccionado.getId();
                String alumno = pagoseleccionado.getAlumno();
                double monto = pagoseleccionado.getMonto();
                String mpago = pagoseleccionado.getMetodoPago();   
                txtId.setText(id);
                cbAlumno.setValue(alumno);
                spMonto.getValueFactory().setValue(monto);                
                cbMPago.setValue(mpago);
            }
        });
        btnAgregar.setDisable(true);
        btnModificar.setDisable(false);
    }
    
    public void RecuperarFields(int op) 
    {
        String Id = txtId.getText();
        String alumno = (String) cbAlumno.getSelectionModel().getSelectedItem();
        double monto = (double) spMonto.getValue();
        String mpago = (String) cbMPago.getSelectionModel().getSelectedItem();
        if(op==1)
        {
            AgregarDatos(alumno, monto, mpago);
        }
        else
        {
            if(op==2)
            {
                ModificarDatos(Id, alumno, monto, mpago);
            }
        }
    }
    
    public void AgregarDatos(String alumno, double monto, String mpago) 
    {
        cPagos asignatura = new cPagos(null, null, monto, mpago);
        cConexion con = new cConexion();
        boolean exito = con.agregarPago(asignatura, alumno);

        if (exito) 
        {            
            Alert alert = new Alert(AlertType.INFORMATION);
            String[] Mensaje = {"Éxito","Pago exitoso","El Pago se ha agregado correctamente."};
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
            String[] Mensaje = {"Error","Error al intentar Pago","Ha ocurrido un error al intentar registrar el Pago."};
            Mensaje(alert, Mensaje);
        }
    }
    
    public void ModificarDatos(String Id, String alumno, double monto, String mpago) 
    {
        cPagos pago = new cPagos(Id, null, monto, mpago);
        cConexion con = new cConexion();
        boolean exito = con.modificarPago(pago, alumno);

        if (exito) 
        {            
            Alert alert = new Alert(AlertType.INFORMATION);
            String[] Mensaje = {"Éxito","Pago modificado","El Pago se ha modificado correctamente."};
            Mensaje(alert, Mensaje);
            Nuevo();
            CargarDatos();
        } 
        else 
        {
            // Mostrar una alerta de error
            Alert alert = new Alert(AlertType.ERROR);
            String[] Mensaje = {"Error","Error al modificar Pago","Ha ocurrido un error al intentar modificar el Pago."};
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
        spMonto.getValueFactory().setValue(2500.0);
        cbAlumno.requestFocus();
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


}*/


