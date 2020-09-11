package domain.entities;

import com.google.common.hash.Hashing;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadPersistente{

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String username;

    @Column
    private String password;

    @Column(columnDefinition = "DATE")
    private LocalDate fechaNacimiento;

    @Column(nullable = true)
    private Integer telefono;

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private Rol rolDefinido;

    public Usuario(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRolDefinido() {
        return rolDefinido;
    }

    public void setRolDefinido(Rol rolDefinido) {
        this.rolDefinido = rolDefinido;
    }
}
