package ec.edu.espe.ac.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class RegistroCivil implements Serializable {
    @Id
    private String cedula;
    private String apellidos;
    private String nombres;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;
    private int codProvincia;
    private String genero;
    private String codEstadoCivil;
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(int codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCodEstadoCivil() {
        return codEstadoCivil;
    }

    public void setCodEstadoCivil(String codEstadoCivil) {
        this.codEstadoCivil = codEstadoCivil;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.cedula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegistroCivil other = (RegistroCivil) obj;
        if (!Objects.equals(this.cedula, other.cedula)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RegistroCivil{" + "cedula=" + cedula + ", apellidos=" + apellidos + ", nombres=" + nombres + ", fechaNacimiento=" + fechaNacimiento + ", codProvincia=" + codProvincia + ", genero=" + genero + ", codEstadoCivil=" + codEstadoCivil + '}';
    }
    
}
