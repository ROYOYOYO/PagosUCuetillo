/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjpa.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(catalog = "openjpa", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Carrera.seleccionaTodos", query = "SELECT c FROM Carrera c"),
    @NamedQuery(name = "Carrera.seleccionaPorId", query = "SELECT c FROM Carrera c WHERE c.carreraId = :carreraId"),
    @NamedQuery(name = "Carrera.seleccionaPorDescripcion", query = "SELECT c FROM Carrera c WHERE c.descripcion = :descripcion")})

public class Carrera implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "carrera_id", nullable = false)
    private Integer carreraId;
    @Column(length = 20)
    private String descripcion;
    @Column
    private int semestres;
    @Column
    private double costoCredito;

    public Carrera() {
        this(null,0,0);
    }

    public Carrera(String Descripcion, int Semestres, double CostoCredito) {
        this.descripcion = Descripcion;
        this.semestres = Semestres;
        this.costoCredito = CostoCredito;
    }

    public Integer getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(Integer carreraid) {
        this.carreraId = carreraid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    public int getSemestres() {
        return semestres;
    }

    public void setSemestres(int Semestres) {
        this.semestres = Semestres;
    }

    public double getCostoCredito() {
        return costoCredito;
    }

    public void setCostoCredito(double CostoCredito) {
        this.costoCredito = CostoCredito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carreraId != null ? carreraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrera)) {
            return false;
        }
        Carrera other = (Carrera) object;
        if ((this.carreraId == null && other.carreraId != null) || (this.carreraId != null && !this.carreraId.equals(other.carreraId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.openjpa.Carrera[ carreraId=" + carreraId + " ]";
    }
    

}
