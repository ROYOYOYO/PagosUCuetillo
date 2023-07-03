/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjpa.entidades;

/**
 *
 * @author AJ
 */
public class Carrera {
    private String id;
    private String Descripcion;
    private int Semestres;
    private double CostoCredito;

    public Carrera(String id, String Descripcion, int Semestres, double CostoCredito) {
        this.id = id;
        this.Descripcion = Descripcion;
        this.Semestres = Semestres;
        this.CostoCredito = CostoCredito;
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

    public int getSemestres() {
        return Semestres;
    }

    public void setSemestres(int Semestres) {
        this.Semestres = Semestres;
    }

    public double getCostoCredito() {
        return CostoCredito;
    }

    public void setCostoCredito(float CostoCredito) {
        this.CostoCredito = CostoCredito;
    }

}
