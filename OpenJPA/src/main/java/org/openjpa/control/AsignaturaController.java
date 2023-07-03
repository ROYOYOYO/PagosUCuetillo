/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjpa.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    private TableView<String> tblAlumnos;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AgregarDatos(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
    }

    @FXML
    private void RecuperarDatos(MouseEvent event) {
    }

    @FXML
    private void ModifDatos(ActionEvent event) {
    }

    @FXML
    private void Nuevo(ActionEvent event) {
    }
    
}
