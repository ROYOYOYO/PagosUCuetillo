/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjpa.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.openjpa.OpenJPA;

/**
 * FXML Controller class
 *
 * @author AJ
 */

public class LoginController {
    private OpenJPA mainApp;

    public void setMainApp(OpenJPA mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtContraseña;
    @FXML
    private Button btnIngresar;
    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void Ingresar()
    {
        mainApp.initRootLayout();
        mainApp.MostrarVentana("Inicio");
    }
    
}
