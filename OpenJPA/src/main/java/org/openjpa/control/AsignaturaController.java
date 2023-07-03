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
import javafx.scene.control.RadioButton;
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
public class AsignaturaController implements Initializable {

    @FXML
    private TextField txtDescripcion;
    @FXML
    private Button btnAgregar;
    @FXML
    private TableView tblAlumnos;
    @FXML
    private Spinner<Integer> spCreditos;
    @FXML
    private ComboBox<String> cbCarrera;
    @FXML
    private ComboBox<String> cbSemestre;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnModificar;

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
        SpinnerValueFactory<Integer> edadValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        spCreditos.setValueFactory(edadValueFactory);        
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

        TableColumn<cAlumno, String> semestreColumn = new TableColumn<>("Semestre");
        semestreColumn.setCellValueFactory(new PropertyValueFactory<>("semestre"));

        TableColumn<cAlumno, Integer> creditosColumn = new TableColumn<>("Creditos");
        creditosColumn.setCellValueFactory(new PropertyValueFactory<>("numeroCreditos"));
        
        TableColumn<cAlumno, String> carreraColumn = new TableColumn<>("Carrera");
        carreraColumn.setCellValueFactory(new PropertyValueFactory<>("carrera"));


        // Agregar las columnas al TableView
        tblAlumnos.getColumns().addAll(idColumn, descripcionColumn, semestreColumn, creditosColumn, carreraColumn);
    }
    
    public void CargarDatos()
    {        
        cConexion con = new cConexion();
        Map<String, String> carreras = con.obtenerCarreras();
        Map<String, String> semestres = con.obtenerSemestres();
        ObservableList<String> opcionesCarreras = FXCollections.observableArrayList(carreras.values());
        ObservableList<String> opcioneSemestres = FXCollections.observableArrayList(semestres.values());

        cbCarrera.setItems(opcionesCarreras);
        cbSemestre.setItems(opcioneSemestres);

        List<cAsignaturas> asignatura = con.obtenerDatosAsignatura();
        ObservableList<cAsignaturas> data = FXCollections.observableArrayList(asignatura);
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
                cAsignaturas alumnoSeleccionado =  (cAsignaturas) newSelection;
                String id = alumnoSeleccionado.getId();
                String descripcion = alumnoSeleccionado.getDescripcion();
                int creditos = alumnoSeleccionado.getNumeroCreditos();
                String carrera = alumnoSeleccionado.getCarrera();                
                String semestre = alumnoSeleccionado.getSemestre();
                txtId.setText(id);
                txtDescripcion.setText(descripcion);
                spCreditos.getValueFactory().setValue(creditos);                
                cbCarrera.setValue(carrera);
                cbSemestre.setValue(semestre);
            }
        });
        btnAgregar.setDisable(true);
        btnModificar.setDisable(false);
    }
    
    public void RecuperarFields(int op) 
    {
        String Id = txtId.getText();
        String descripcion = txtDescripcion.getText();
        int creditos = (int) spCreditos.getValue();
        String carrera = (String) cbCarrera.getSelectionModel().getSelectedItem();
        String semestre = (String) cbSemestre.getSelectionModel().getSelectedItem();
        if(op==1)
        {
            AgregarDatos(descripcion, semestre, creditos, carrera);
        }
        else
        {
            if(op==2)
            {
                ModificarDatos(Id, descripcion, semestre, creditos, carrera);
            }
        }
    }
    
    public void AgregarDatos(String Descripcion, String Semestre, int numeroCreditos, String Carrera) 
    {
        cAsignaturas asignatura = new cAsignaturas(null, Descripcion, null, numeroCreditos, null);
        cConexion con = new cConexion();
        boolean exito = con.agregarAsignatura(asignatura, Semestre, Carrera);

        if (exito) 
        {            
            Alert alert = new Alert(AlertType.INFORMATION);
            String[] Mensaje = {"Éxito","Asignatura agregada","La Asignatura se ha agregado correctamente."};
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
            String[] Mensaje = {"Error","Error al agregar Asignatura","Ha ocurrido un error al intentar agregar la Asignatura."};
            Mensaje(alert, Mensaje);
        }
    }
    
    public void ModificarDatos(String Id, String Descripcion, String Semestre, int numeroCreditos, String Carrera) 
    {
        cAsignaturas asignatura = new cAsignaturas(Id, Descripcion, null, numeroCreditos, null);
        cConexion con = new cConexion();
        boolean exito = con.modificarAsignatura(asignatura, Semestre, Carrera);

        if (exito) 
        {            
            Alert alert = new Alert(AlertType.INFORMATION);
            String[] Mensaje = {"Éxito","Asignatura modificado","La Asignatura se ha modificado correctamente."};
            Mensaje(alert, Mensaje);
            Nuevo();
            CargarDatos();
        } 
        else 
        {
            // Mostrar una alerta de error
            Alert alert = new Alert(AlertType.ERROR);
            String[] Mensaje = {"Error","Error al modificar Asignatura","Ha ocurrido un error al intentar modificar la Asignatura."};
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
        cbCarrera.getSelectionModel().select(0);
        cbSemestre.getSelectionModel().select(0);
        spCreditos.getValueFactory().setValue(16);
        txtDescripcion.requestFocus();
        btnModificar.setDisable(true);
    }
    public void DesActivar(boolean a)
    {
        txtDescripcion.setDisable(a);
        cbCarrera.setDisable(a);
        cbSemestre.setDisable(a);
        spCreditos.setDisable(a);
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


