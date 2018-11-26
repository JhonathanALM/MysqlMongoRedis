package ec.edu.espe.ac.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(noClassnameStored =true , value="Registrocivil")
public class RegistroCivilM implements Serializable {
    @Id
    @Property("cedula")
    private String cedula;
    @Property("apellidos")
    private String apellidos;
    @Property("nombres")
    private String nombres;
    @Property("fechaNacimiento")
    private Date fechaNacimiento;
    @Property("codProvincia")
    private int codProvincia;
    @Property("genero")
    private String genero;
    @Property("codEstadoCivil")
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
        final RegistroCivilM other = (RegistroCivilM) obj;
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
