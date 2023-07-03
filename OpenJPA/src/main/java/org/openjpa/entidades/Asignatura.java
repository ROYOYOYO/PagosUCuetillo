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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(catalog = "openjpa", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Asignatura.seleccionaTodos", query = "SELECT a FROM Asignatura a"),
    @NamedQuery(name = "Asignatura.seleccionaPorId", query = "SELECT a FROM Asignatura a WHERE a.asignaturaId = :asignaturaId"),
    @NamedQuery(name = "Asignatura.seleccionaPorDescripcion", query = "SELECT a FROM Asignatura a WHERE a.descripcion = :descripcion")})
public class Asignatura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "asignatura_id", nullable = false)
    private Integer asignaturaId;
    @Column(length = 20)
    private String descripcion;
    @Column
    private int numeroCreditos;
    @ManyToOne
    @JoinColumn(name = "semestre", referencedColumnName = "semestre_id")
    private Semestre semestre;
    @ManyToOne
    @JoinColumn(name = "carrera", referencedColumnName = "carrera_id")
    private Carrera carrera;
    
    public Asignatura() {
        this(null, null, 0, null);    
    }

    public Asignatura(String Descripcion, Semestre semestre, int numeroCreditos, Carrera carrera) {
        this.descripcion = Descripcion;
        this.semestre = semestre;
        this.numeroCreditos = numeroCreditos;
        this.carrera = carrera;
    }

    public Integer getAsignaturaId() {
        return asignaturaId;
    }

    public void setAsignaturaId(Integer asignaturaid) {
        this.asignaturaId = asignaturaid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public int getNumeroCreditos() {
        return numeroCreditos;
    }

    public void setNumeroCreditos(int numeroCreditos) {
        this.numeroCreditos = numeroCreditos;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asignaturaId != null ? asignaturaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.asignaturaId == null && other.asignaturaId != null) || (this.asignaturaId != null && !this.asignaturaId.equals(other.asignaturaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.openjpa.Asignatura[ asignaturaid=" + asignaturaId + " ]";
    }
    
}

