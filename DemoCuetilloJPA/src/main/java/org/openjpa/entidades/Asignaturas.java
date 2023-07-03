/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjpa.entidades;

/**
 *
 * @author AJ
 */
public class Asignaturas {
    private String id;
    private String Descripcion;
    private String Semestre;
    private int numeroCreditos;
    private String Carrera;

    public Asignaturas(String id, String Descripcion, String Semestre, int numeroCreditos, String Carrera) {
        this.id = id;
        this.Descripcion = Descripcion;
        this.Semestre = Semestre;
        this.numeroCreditos = numeroCreditos;
        this.Carrera = Carrera;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getSemestre() {
        return Semestre;
    }

    public void setSemestre(String Semestre) {
        this.Semestre = Semestre;
    }

    public int getNumeroCreditos() {
        return numeroCreditos;
    }

    public void setNumeroCreditos(int numeroCreditos) {
        this.numeroCreditos = numeroCreditos;
    }

    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
    }
    
}

