package app.recursoshumanos.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
private String nombre;


    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private List<Empleado> empleados;

    public Departamento() {
        // Constructor vacío requerido por JPA
    }

    public Departamento(String nombre) {
        this.nombre = nombre;
    }
}
