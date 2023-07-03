/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.openjpa.control.AlumnoControl;
import org.openjpa.control.AsignaturaControl;
import org.openjpa.control.CarreraControl;
import org.openjpa.control.LoginController;
import org.openjpa.control.PagoControl;
import org.openjpa.control.RootLayoutController;
import org.openjpa.control.SemestreControl;
import org.openjpa.control.exceptions.EntidadPreexistenteException;
import org.openjpa.entidades.Alumno;
import org.openjpa.entidades.Asignatura;
import org.openjpa.entidades.Carrera;
import org.openjpa.entidades.Pago;
import org.openjpa.entidades.Semestre;

/**
 *
 * @author AJ
 */
public class OpenJPA extends Application{
    private Stage primaryStage;
    private BorderPane rootLayout;
    public static void main(String[] args) {
        launch(args);
        Alumno alumno;
        Asignatura asignatura;
        Carrera carrera;
        Pago pago;
        Semestre semestre;
        // Creamos la factoría de entity managers y un entity manager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseDatos");


        AlumnoControl alumnoControl = new AlumnoControl(emf);
        AsignaturaControl asignaturaControl = new AsignaturaControl(emf);
        CarreraControl carreraControl = new CarreraControl(emf);
        PagoControl pagoControl = new PagoControl(emf);
        SemestreControl semestreControl = new SemestreControl(emf);

        //Pedimos datos del autor
        String nombre = leerTexto("Introduce nombre: ");
        String apellidos = leerTexto("Introduce apellidos: ");        
        int edad = leerNumero("Introduce la edad: ");
        int dni = leerNumero("Introduce su dni: ");
        String Genero = leerTexto("Introduce genero: ");
        Date fechaActual = new Date();

        carrera = new Carrera("Ingeniería", 10, 150.0);
        semestre = new Semestre("Primer Semestre", 1, fechaActual, fechaActual, 20,22);
        try {
            // Lo añadimos a la BD
            System.out.println("Identificador del autor: " + semestreControl.insertar(semestre));
        } catch (EntidadPreexistenteException ex) {
            Logger.getLogger(OpenJPA.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Lo añadimos a la BD
            System.out.println("Identificador del autor: " + carreraControl.insertar(carrera));
        } catch (EntidadPreexistenteException ex) {
            Logger.getLogger(OpenJPA.class.getName()).log(Level.SEVERE, null, ex);
        }
        alumno = new Alumno(nombre, apellidos, dni, edad, Genero, carrera, semestre, fechaActual);
        try {
            // Lo añadimos a la BD
            System.out.println("Identificador del autor: " + alumnoControl.insertar(alumno));
        } catch (EntidadPreexistenteException ex) {
            Logger.getLogger(OpenJPA.class.getName()).log(Level.SEVERE, null, ex);
        }

    //        // Creamos el mensaje
    //        MensajeControl mensajeControl = new MensajeControl(emf);
    //        
    //        String mensajeStr = leerTexto("Introduce mensaje: ");
    //        Mensaje mens = new Mensaje(mensajeStr, persona);
    //        // Establecemos los campos
    //        mens.setFecha(new Date());
    //        // Y lo guardamos en la BD
    //        int idMensaje = 0;
    //        try {
    //            idMensaje = mensajeControl.insertar(mens);
    //        } catch (EntidadPreexistenteException ex) {
    //            Logger.getLogger(OpenJPA.class.getName()).log(Level.SEVERE, null, ex);
    //        }
    //        System.out.println("Identificador del mensaje: " + idMensaje);
    //        
    //        System.out.println("============================================");
    //
    //        List<Persona> results = personaControl.buscaPersonas();
    //        for(Persona p : results){
    //            System.out.println(p);
    //        }
    //        
    //        System.out.println("============================================");
    //        // Marcamos el comienzo de la transacción


    }
 

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sistema de Pagos Cuetillo");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("control/Login.fxml"));
        AnchorPane root = loader.load();
        LoginController loginController = loader.getController();
        loginController.setMainApp(this);
        // Crear una escena y asignarla a la ventana principal
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
//        initRootLayout();
//        MostrarVentana("Inicio");
    }
    
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OpenJPA.class.getResource("control/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    
    public void MostrarVentana(String ventana) 
    {
        try 
        {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(OpenJPA.class.getResource("control/"+ventana+".fxml"));
            AnchorPane Ventana = (AnchorPane) loader.load();
            rootLayout.setCenter(Ventana);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Stage getPrimaryStage() {
            return primaryStage;
    }

    
    static private String leerTexto(String mensaje) {
        String texto;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(mensaje);
            texto = in.readLine();
        } catch (IOException e) {
            texto = "Error";
        }
        return texto;
    }
    
    static private int leerNumero(String mensaje) {
        int numero;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(mensaje);
            numero = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            numero = -1;
        }
        return numero;
    }
    
    static private char leerCaracter(String mensaje) {
        char caracter;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(mensaje);
            caracter = (in.readLine().charAt(0));
        } catch (IOException e) {
            caracter = 'E';
        }
        return caracter;
    }
}
