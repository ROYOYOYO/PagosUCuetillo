/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjpa.control;

import java.net.URL;
import java.util.List;
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
public class CarreraController implements Initializable {

    @FXML
    private TextField txtDescripcion;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnSalir;
    @FXML
    private TableView tblAlumnos;
    @FXML
    private Spinner<Double> spCredito;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnNuevo;
    @FXML
    private ComboBox<Integer> cbSemestre;
    @FXML
    private TextField txtId;    
    

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
        SpinnerValueFactory<Double> creditoValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(100.0, 150.0);
        spCredito.setValueFactory(creditoValueFactory);  
        
        cbSemestre.getItems().addAll(10, 12, 14);
    }       
}
    /*
    public void InicializarTable()
    {
        TableColumn<cAlumno, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setVisible(false);

        TableColumn<cAlumno, String> descripcionColumn = new TableColumn<>("Descripcion");
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));

        TableColumn<cAlumno, Integer> semestresColumn = new TableColumn<>("Semestres");
        semestresColumn.setCellValueFactory(new PropertyValueFactory<>("Semestres"));
        
        TableColumn<cAlumno, Integer> costoColumn = new TableColumn<>("CostoCredito");
        costoColumn.setCellValueFactory(new PropertyValueFactory<>("CostoCredito"));

        // Agregar las columnas al TableView
        tblAlumnos.getColumns().addAll(idColumn, descripcionColumn, semestresColumn, costoColumn);
    }
    
    public void CargarDatos()
    {        
        cConexion con = new cConexion();
        List<cCarrera> carrera = con.obtenerDatosCarreras();
        ObservableList<cCarrera> data = FXCollections.observableArrayList(carrera);
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
                cCarrera carreraSeleccionada =  (cCarrera) newSelection;
                String id = carreraSeleccionada.getId();
                String Descripcion = carreraSeleccionada.getDescripcion();                
                int Semestre = carreraSeleccionada.getSemestres();  
                double costocredito = carreraSeleccionada.getCostoCredito(); 
                txtId.setText(id);
                txtDescripcion.setText(Descripcion);               
                cbSemestre.setValue(Semestre);          
                spCredito.getValueFactory().setValue(costocredito); 
            }
        });
        btnAgregar.setDisable(true);
        btnModificar.setDisable(false);
    }
    
    public void RecuperarFields(int op) 
    {
        String Id = txtId.getText();
        String Descripcion = txtDescripcion.getText();     
        int Semestre = (int) cbSemestre.getSelectionModel().getSelectedItem();
        double costoCredito = (double) spCredito.getValue();
        if(op==1)
        {
            AgregarDatos(Descripcion, Semestre, costoCredito);
        }
        else
        {
            if(op==2)
            {
                ModificarDatos(Id, Descripcion, Semestre, costoCredito);
            }
        }
    }
    
    public void AgregarDatos(String Descripcion,int Semestres,double CostoCredito) 
    {
        cCarrera carrera = new cCarrera(null, Descripcion, Semestres, CostoCredito);
        cConexion con = new cConexion();
        boolean exito = con.agregarCarrera(carrera);

        if (exito) 
        {            
            Alert alert = new Alert(AlertType.INFORMATION);
            String[] Mensaje = {"Éxito","Registro exitoso","La Carrera se ha agregado correctamente."};
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
            String[] Mensaje = {"Error","Error al intentar ingresar","Ha ocurrido un error al intentar registrar la Carrera."};
            Mensaje(alert, Mensaje);
        }
    }
    
    public void ModificarDatos(String Id, String Descripcion,int Semestre,double CostoCredito) 
    {
        cCarrera carrera = new cCarrera(Id, Descripcion, Semestre, CostoCredito);
        cConexion con = new cConexion();
        boolean exito = con.modificarCarrera(carrera);

        if (exito) 
        {            
            Alert alert = new Alert(AlertType.INFORMATION);
            String[] Mensaje = {"Éxito","Carrera modificada","La Carrera se ha modificado correctamente."};
            Mensaje(alert, Mensaje);
            Nuevo();
            CargarDatos();
        } 
        else 
        {
            // Mostrar una alerta de error
            Alert alert = new Alert(AlertType.ERROR);
            String[] Mensaje = {"Error","Error al modificar Carrera","Ha ocurrido un error al intentar modificar la Carrera."};
            Mensaje(alert, Mensaje);
        }
    }
    
     @FXML
    public void Nuevo() 
    {
        DesActivar(false);
        btnAgregar.setDisable(false);
        txtId.setText("");
        txtDescripcion.setText("");
        cbSemestre.getSelectionModel().select(0);
        spCredito.getValueFactory().setValue(127.0);
        txtDescripcion.requestFocus();
        btnModificar.setDisable(true);
    }
    public void DesActivar(boolean a)
    {
        txtDescripcion.setDisable(a);
        cbSemestre.setDisable(a);
        spCredito.setDisable(a);
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

*/
