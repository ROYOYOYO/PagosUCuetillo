package org.openjpa.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Alumno.seleccionaTodos", query = "SELECT a FROM Alumno a"),
    @NamedQuery(name = "Alumno.seleccionaPorId", query = "SELECT a FROM Alumno a WHERE a.alumnoId = :alumnoId"),
    @NamedQuery(name = "Alumno.seleccionaPorApellidos", query = "SELECT a FROM Alumno a WHERE a.apellidos = :apellidos")})

public class Alumno implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "alumno_id", nullable = false)
    private Integer alumnoId;
    @Column(length = 20)
    private String nombre;
    @Column(length = 40)
    private String apellidos;
    @Column
    private int edad;
    @Column
    private int dni;
    @Column
    private char genero;
    @Column(length = 10)
    private String carrera;
    @Column(length = 10)
    private String semestre;    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;

    public Alumno() {
        this(null, null, 0, 0, '0', null, null, null);
    }    

    public Alumno(String nombre, String apellidos, int edad, int dni, char Genero, String Carrera, String Semestre, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.dni = dni;
        this.genero = Genero;
        this.carrera = Carrera;
        this.semestre = Semestre;
        this.fechaNacimiento = new Date(2005, 06, 15);
    }

 
    public Integer getAlumnoId() {
        return alumnoId;
    }

    public void setPersonaId(Integer personaId) {
        this.alumnoId = personaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char Genero) {
        this.genero = Genero;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String Carrera) {
        this.carrera = Carrera;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String Semestre) {
        this.semestre = Semestre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (alumnoId != null ? alumnoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.alumnoId == null && other.alumnoId != null) || (this.alumnoId != null && !this.alumnoId.equals(other.alumnoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.openjpa.Alumno[ alumnoId=" + alumnoId + " ]";
    }
    
}
