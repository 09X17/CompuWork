package app.recursoshumanos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;
    private String cargo;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    public String getTipo() {
        return this.getClass().getSimpleName().toUpperCase();
    }
}