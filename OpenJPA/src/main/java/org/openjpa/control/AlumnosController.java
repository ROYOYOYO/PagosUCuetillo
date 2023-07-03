/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjpa.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.openjpa.control.exceptions.EntidadPreexistenteException;
import org.openjpa.entidades.Alumno;
import org.openjpa.entidades.Carrera;
import org.openjpa.entidades.Semestre;
/**
 * FXML Controller class
 *
 * @author AJ
 */
public class AlumnosController implements Initializable {

    @FXML
    private TableView tblAlumnos;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private Spinner spEdad;
    @FXML
    private TextField txtDNI;
    @FXML
    private RadioButton rbMasculino;
    @FXML
    private RadioButton rbFemenino;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnModificar;
    @FXML
    private ComboBox<String> cbCarrera;
    @FXML
    private ComboBox<String> cbSemestre;
    private ObservableList<String> opcionesCarreras;
    private ObservableList<String> opcionesSemestres;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnNuevo;
    private AlumnoControl alumnoControl;
    @FXML
    private DatePicker dtNacimiento;
    
    public AlumnosController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseDatos");
        this.alumnoControl = new AlumnoControl(emf);
        opcionesCarreras = FXCollections.observableArrayList();
        opcionesSemestres = FXCollections.observableArrayList();
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

        ToggleGroup toggleGroup = new ToggleGroup();    
        rbMasculino.setToggleGroup(toggleGroup);
        rbFemenino.setToggleGroup(toggleGroup);
        SpinnerValueFactory<Integer> edadValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        spEdad.setValueFactory(edadValueFactory);        
        rbMasculino.setSelected(true);
        btnModificar.setDisable(true);
        
    }
    
    public void InicializarTable()
    {
        
        TableColumn<Alumno, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("alumnoid"));
        idColumn.setVisible(false);

        TableColumn<Alumno, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Alumno, String> apellidoColumn = new TableColumn<>("Apellidos");
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellidos"));

        TableColumn<Alumno, Integer> edadColumn = new TableColumn<>("Edad");
        edadColumn.setCellValueFactory(new PropertyValueFactory<>("edad"));

        TableColumn<Alumno, Integer> dniColumn = new TableColumn<>("DNI");
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));

        TableColumn<Alumno, Character> generoColumn = new TableColumn<>("Género");
        generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));

        TableColumn<Alumno, String> carreraColumn = new TableColumn<>("Carrera");
        carreraColumn.setCellValueFactory(new PropertyValueFactory<>("carrera"));

        TableColumn<Alumno, String> semestreColumn = new TableColumn<>("Semestre");
        semestreColumn.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        
        TableColumn<Alumno, Date> fechaColumn = new TableColumn<>("Nacimiento");
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));

        // Agregar las columnas al TableView
        tblAlumnos.getColumns().addAll(idColumn, nombreColumn, apellidoColumn, edadColumn, dniColumn, generoColumn, carreraColumn, semestreColumn, fechaColumn);
    }
    
    public void CargarDatos()
    {        
        List<Carrera> carreras = alumnoControl.obtenerCarrerasOrdenadasPorId();
        List<Semestre> semestres = alumnoControl.obtenerSemestresOrdenadosPorId();

        // Convertir las carreras y semestres en listas de descripciones
        List<String> carrerasDescripciones = new ArrayList<>();
        List<String> semestresDescripciones = new ArrayList<>();

        for (Carrera carrera : carreras) {
            carrerasDescripciones.add(carrera.getDescripcion());
        }

        for (Semestre semestre : semestres) {
            semestresDescripciones.add(semestre.getDescripcion());
        }

        // Asignar las listas de descripciones a los ComboBox correspondientes
        ObservableList<String> opcionesCarreras = FXCollections.observableArrayList(carrerasDescripciones);
        ObservableList<String> opcionesSemestres = FXCollections.observableArrayList(semestresDescripciones);

        cbCarrera.setItems(opcionesCarreras);
        cbSemestre.setItems(opcionesSemestres);
        List<Alumno> listaAlumnos = alumnoControl.buscaAlumnos();
        tblAlumnos.getItems().setAll(listaAlumnos);
        
    }
    
    @FXML
    public void RecuperarDatos() 
    {
        DesActivar(false);
        tblAlumnos.getSelectionModel().selectedItemProperty().addListener((ObservableValue obs, Object oldSelection, Object newSelection) -> 
        {
            if (newSelection != null) 
            {
                Alumno alumnoSeleccionado =  (Alumno) newSelection;
                int id = alumnoSeleccionado.getAlumnoId();
                String nombre = alumnoSeleccionado.getNombre();
                String apellido = alumnoSeleccionado.getApellidos();
                int edad = alumnoSeleccionado.getEdad();
                int dni = alumnoSeleccionado.getDni();
                char genero = alumnoSeleccionado.getGenero().charAt(0);
                int carrera = alumnoSeleccionado.getCarrera();                
                int semestre = alumnoSeleccionado.getSemestre();
                Date fecha = alumnoSeleccionado.getFechaNacimiento();
                txtId.setText(""+id);
                txtNombre.setText(nombre);
                txtApellido.setText(apellido);
                spEdad.getValueFactory().setValue(edad);                
                txtDNI.setText(dni+"");
                if(genero == 'M')
                {
                    rbMasculino.setSelected(true);
                }
                else
                {
                    rbFemenino.setSelected(true);
                }
                cbCarrera.getSelectionModel().select(carrera-1);
                cbSemestre.getSelectionModel().select(semestre-1);
            }
        });
        btnAgregar.setDisable(true);
        btnModificar.setDisable(false);
    }
    
    public void RecuperarFields(int op) 
    {
        String Id = txtId.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        int edad = (int) spEdad.getValue();
        int dni = Integer.parseInt(txtDNI.getText());
        char genero = rbMasculino.isSelected() ? 'M' : 'F';
        String carrera = (String) cbCarrera.getSelectionModel().getSelectedItem();
        String semestre = (String) cbSemestre.getSelectionModel().getSelectedItem();
        Date fecha = (Date) dtNacimiento.getDayCellFactory();
        if(op==1)
        {
            AgregarDatos(nombre, apellido, edad, dni, genero, carrera, semestre,fecha);
        }
        else
        {
            if(op==2)
            {
                ModificarDatos(Id, nombre, apellido, edad, dni, genero, carrera, semestre, fecha);
            }
        }
    }
    
    public void AgregarDatos(String nombre, String apellido, int edad, int dni, char genero,String carrera, String semestre, Date nacimiento) 
    {
        Alumno alumno = new Alumno(nombre,apellido,edad,dni,String.valueOf(genero) ,alumnoControl.obtenerCarreraId(carrera),alumnoControl.obtenerSemestreId(semestre), nacimiento);
        int exito = 0;
        try {
            exito = alumnoControl.insertar(alumno);
        } catch (EntidadPreexistenteException ex) {
            Logger.getLogger(AlumnosController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (exito!=0) 
        {            
            Alert alert = new Alert(AlertType.INFORMATION);
            String[] Mensaje = {"Éxito","Alumno agregado","El alumno se ha agregado correctamente."};
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
            String[] Mensaje = {"Error","Error al agregar alumno","Ha ocurrido un error al intentar agregar al alumno."};
            Mensaje(alert, Mensaje);
        }
    }
    
    public void ModificarDatos(String Id,String nombre, String apellido, int edad, int dni, char genero, String carrera, String semestre, Date fecha) 
    {
//        cAlumno alumno = new cAlumno(Id, nombre, apellido, edad, dni, genero, null, null);
//        cConexion con = new cConexion();
//        boolean exito = con.modificarAlumno(alumno, semestre, carrera);
//
//        if (exito) 
//        {            
//            Alert alert = new Alert(AlertType.INFORMATION);
//            String[] Mensaje = {"Éxito","Alumno modificado","El alumno se ha modificado correctamente."};
//            Mensaje(alert, Mensaje);
//            Nuevo();
//            CargarDatos();
//        } 
//        else 
//        {
//            // Mostrar una alerta de error
//            Alert alert = new Alert(AlertType.ERROR);
//            String[] Mensaje = {"Error","Error al modificar alumno","Ha ocurrido un error al intentar modificar al alumno."};
//            Mensaje(alert, Mensaje);
//        }
    }
    
     @FXML
    public void Nuevo() 
    {
        DesActivar(false);
        btnAgregar.setDisable(false);
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");       
        txtDNI.setText("");
        cbCarrera.getSelectionModel().select(0);
        cbSemestre.getSelectionModel().select(0);
        rbMasculino.setSelected(true);
        spEdad.getValueFactory().setValue(16);
        txtNombre.requestFocus();
        btnModificar.setDisable(true);
    }
    public void DesActivar(boolean a)
    {
        txtNombre.setDisable(a);
        txtApellido.setDisable(a);       
        txtDNI.setDisable(a);
        cbCarrera.setDisable(a);
        cbSemestre.setDisable(a);
        rbMasculino.setDisable(a);
        rbFemenino.setDisable(a);
        spEdad.setDisable(a);
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


