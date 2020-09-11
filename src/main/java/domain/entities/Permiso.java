package domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "permiso")
public class Permiso extends EntidadPersistente{

    @Column
    private String nombre;

    public Permiso(){}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
