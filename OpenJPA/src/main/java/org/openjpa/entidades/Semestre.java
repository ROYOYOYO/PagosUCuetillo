/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjpa.entidades;

import java.io.Serializable; 
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(catalog = "openjpa", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Semestre.seleccionaTodos", query = "SELECT s FROM Semestre s"),
    @NamedQuery(name = "Semestre.seleccionaPorId", query = "SELECT s FROM Semestre s WHERE s.semestreId = :semestreId"),
    @NamedQuery(name = "Semestre.seleccionaPorDescripcion", query = "SELECT s FROM Semestre s WHERE s.descripcion = :descripcion")})

public class Semestre implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "semestre_id", nullable = false)
    private Integer semestreId;
    @Column(length = 20)
    private String descripcion;
    @Column
    private int numero;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column
    private int creditosMin;
    @Column
    private int creditosActuales;
    @OneToMany(mappedBy = "semestre", cascade = CascadeType.ALL)
    private List<Alumno> alumnos;
    @OneToMany(mappedBy = "semestre", cascade = CascadeType.ALL)
    private List<Asignatura> asignatura;
    
    public Semestre() {
        this(null,0,null,null,0,0);
    }

    public Semestre(String descripcion, int numero, Date fechaInicio, Date fechaFin, int creditosMin, int creditosActuales) {
        this.descripcion = descripcion;
        this.numero = numero;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.creditosMin = creditosMin;
        this.creditosActuales = creditosActuales;
    }

    public Integer getSemestreId() {
        return semestreId;
    }

    public void setSemestreId(Integer semestreId) {
        this.semestreId = semestreId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCreditosMin() {
        return creditosMin;
    }

    public void setCreditosMin(int creditosMin) {
        this.creditosMin = creditosMin;
    }

    public int getCreditosActuales() {
        return creditosActuales;
    }

    public void setCreditosActuales(int creditosActuales) {
        this.creditosActuales = creditosActuales;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (semestreId != null ? semestreId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Semestre)) {
            return false;
        }
        Semestre other = (Semestre) object;
        if ((this.semestreId == null && other.semestreId != null) || (this.semestreId != null && !this.semestreId.equals(other.semestreId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.openjpa.Semestre[ semestreId=" + semestreId + " ]";
    }
}
