/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjpa.entidades;

import java.time.LocalDate;

/**
 *
 * @author AJ
 */
public class Semestre {
    private String id;
    private String Descripcion;
    private int numero;
    private LocalDate FechaInicio;
    private LocalDate FechaFin;
    private int CreditosMin;
    private int CreditosActuales;

    public Semestre(String id, String descripcion, int numero, LocalDate fechaInicio, LocalDate fechaFin, int creditosMin, int creditosActuales) {
        this.id = id;
        this.Descripcion = descripcion;
        this.numero = numero;
        this.FechaInicio = fechaInicio;
        this.FechaFin = fechaFin;
        this.CreditosMin = creditosMin;
        this.CreditosActuales = creditosActuales;
    }
    
    
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.FechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.FechaFin = fechaFin;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getCreditosMin() {
        return CreditosMin;
    }

    public void setCreditosMin(int CreditosMin) {
        this.CreditosMin = CreditosMin;
    }

    public int getCreditosActuales() {
        return CreditosActuales;
    }

    public void setCreditosActuales(int CreditosActuales) {
        this.CreditosActuales = CreditosActuales;
    }
    
    
}
