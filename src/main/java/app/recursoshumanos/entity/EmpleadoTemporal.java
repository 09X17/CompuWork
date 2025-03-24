package app.recursoshumanos.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("TEMPORAL")
@Getter
@Setter
public class EmpleadoTemporal extends Empleado {

    private double pagoPorHora;
    private int horasTrabajadas;

    @Override
public String getTipo() {
    return "TEMPORAL";
}

}
