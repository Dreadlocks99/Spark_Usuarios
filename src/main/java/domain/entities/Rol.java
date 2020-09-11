package domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rol")
public class Rol extends EntidadPersistente{

    @Column
    private String nombre;

    @ManyToMany
    private List<Permiso> permisos;

    public Rol(){}

    public Rol(String nombre){
        this.nombre = nombre;
        this.permisos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }


}
