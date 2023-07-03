package org.openjpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.openjpa.control.AlumnoControl;
import org.openjpa.control.exceptions.EntidadPreexistenteException;
import org.openjpa.entidades.Alumno;

public class OpenJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Alumno alumno;
        // Creamos la factoría de entity managers y un entity manager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BaseDatos");
              
        
        AlumnoControl alumnoControl = new AlumnoControl(emf);
         // Pedimos datos del autor
        String nombre = leerTexto("Introduce nombre: ");
        String apellidos = leerTexto("Introduce apellidos: ");        
        int edad = leerNumero("Introduce la edad: ");
        int dni = leerNumero("Introduce su dni: ");
        char Genero = leerCaracter("Introduce genero: ");
        String Carrera = leerTexto("Introduce la carrera: ");
        String Semestre = leerTexto("Introduce el semestre: ");
        Date fechaActual = new Date();
        alumno = new Alumno(nombre, apellidos, dni, edad, Genero, Carrera, Semestre, fechaActual);
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
