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
import org.openjpa.entidades.Asignatura;
import org.openjpa.entidades.Carrera;
import org.openjpa.entidades.Semestre;

public class AsignaturaController implements Initializable {

    private AsignaturaControl asignaturaControl;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Button btnAgregar;
    @FXML
    private TableView tblAlumnos;
    @FXML
    private Spinner<Integer> spCreditos;
    @FXML
    private Button btnModificar;
    @FXML
    private ComboBox<String> cbCarrera;
    @FXML
    private ComboBox<String> cbSemestre;
    @FXML
    private TextField txtId;
    private ObservableList<String> opcionesCarreras;
    private ObservableList<String> opcionesSemestres;
    
    public AsignaturaController() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseDatos");
        this.asignaturaControl = new AsignaturaControl(emf);
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
        SpinnerValueFactory<Integer> edadValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(18, 28);
        spCreditos.setValueFactory(edadValueFactory);        
        btnModificar.setDisable(true);
        
    }
    
    public void InicializarTable()
    {        
        TableColumn<Asignatura, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setVisible(false);

        TableColumn<Asignatura, String> descripcionColumn = new TableColumn<>("Descripcion");
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<Asignatura, String> semestreColumn = new TableColumn<>("semestre");
        semestreColumn.setCellValueFactory(cellData -> {
            Asignatura asignatura = cellData.getValue();
            int semestreId = asignatura.getSemestre();
            if (semestreId != 0) { // Verificar si el ID es válido
                // Obtener la entidad Semestre según el ID
                Semestre semestre = asignaturaControl.obtenerSemestrePorId(semestreId);
                if (semestre != null) {
                    return new SimpleStringProperty(semestre.getDescripcion());
                }
            }
            return new SimpleStringProperty("");
        });
        
        TableColumn<Asignatura, Integer> dniColumn = new TableColumn<>("Creditos");
        dniColumn.setCellValueFactory(new PropertyValueFactory<>("numeroCreditos"));

        TableColumn<Asignatura, String> carreraColumn = new TableColumn<>("Carrera");
        carreraColumn.setCellValueFactory(cellData -> {
        Asignatura asignatura = cellData.getValue();
        int carreraId = asignatura.getCarrera();
        if (carreraId != 0) { // Verificar si el ID es válido
            // Obtener la entidad Carrera según el ID
            Carrera carrera = asignaturaControl.obtenerCarreraPorId(carreraId);
            if (carrera != null) {
                return new SimpleStringProperty(carrera.getDescripcion());
            }
        }
        return new SimpleStringProperty("");
        });
        // Agregar las columnas al TableView
        tblAlumnos.getColumns().addAll(idColumn, descripcionColumn, semestreColumn, dniColumn, carreraColumn);
    }
    
    public void CargarDatos()
    {        
        List<Carrera> carreras = asignaturaControl.obtenerCarrerasOrdenadasPorId();
        List<Semestre> semestres = asignaturaControl.obtenerSemestresOrdenadosPorId();

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
        List<Asignatura> listaAlumnos = asignaturaControl.buscaAsignaturas();
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
                Asignatura asignaturaSeleccionado =  (Asignatura) newSelection;
                int id = asignaturaSeleccionado.getAsignaturaId();
                String descripcion = asignaturaSeleccionado.getDescripcion();
                int semestre = asignaturaSeleccionado.getSemestre();
                int creditos = asignaturaSeleccionado.getNumeroCreditos();
                int carrera = asignaturaSeleccionado.getCarrera();                
                
                
                txtId.setText(""+id);
                txtDescripcion.setText(descripcion);
                cbCarrera.getSelectionModel().select(carrera-1);
                cbSemestre.getSelectionModel().select(semestre-1);
                spCreditos.getValueFactory().setValue(creditos);
            }
        });
        btnAgregar.setDisable(true);
        btnModificar.setDisable(false);
    }
    
    public void RecuperarFields(int op) 
    {
        String descripcion = txtDescripcion.getText();
        int creditos = (int) spCreditos.getValue();;
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
                int Id = Integer.parseInt(txtId.getText());
                ModificarDatos(Id, descripcion, semestre, creditos, carrera);
            }
        }
    }
    
    public void AgregarDatos(String Descripcion, String semestre, int numeroCreditos, String carrera) 
    {
        Asignatura asignatura = new Asignatura(Descripcion,asignaturaControl.obtenerSemestreId(semestre),numeroCreditos,asignaturaControl.obtenerCarreraId(carrera));
        int exito = 0;
        try {
            exito = asignaturaControl.insertar(asignatura);
        } catch (EntidadPreexistenteException ex) {
            Logger.getLogger(AlumnosController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (exito!=0) 
        {            
            Alert alert = new Alert(AlertType.INFORMATION);
            String[] Mensaje = {"Éxito","Asignatura agregada","La asignatura se ha agregado correctamente."};
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
            String[] Mensaje = {"Error","Error al agregar asignatura","Ha ocurrido un error al intentar agregar la asignatura."};
            Mensaje(alert, Mensaje);
        }
    }
    
    public void ModificarDatos(int Id,String Descripcion, String semestre, int numeroCreditos, String carrera) 
    {
        Asignatura asignatura = new Asignatura(Descripcion, asignaturaControl.obtenerSemestreId(semestre), numeroCreditos, asignaturaControl.obtenerCarreraId(carrera));
        asignatura.setAsignaturaId(Id);
        int exito =0;
        try {
            exito = asignaturaControl.editar(asignatura);
        } catch (NoExisteEntidadException ex) {
            Logger.getLogger(AlumnosController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (exito!=0)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            String[] Mensaje = {"Éxito","Asignatura modificada","La Asignatura se ha modificado correctamente."};
            Mensaje(alert, Mensaje);
            Nuevo();
            CargarDatos();
            DesActivar(true);
        }
        else
        {
            // Mostrar una alerta de error
            Alert alert = new Alert(AlertType.ERROR);
            String[] Mensaje = {"Error","Error al modificar asignatura","Ha ocurrido un error al intentar modificar la asignatura."};
            Mensaje(alert, Mensaje);
        }     
    }
    
    @FXML
    public void Nuevo() 
    {
        DesActivar(false);
        btnAgregar.setDisable(false);
        txtId.setText("");
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


}


