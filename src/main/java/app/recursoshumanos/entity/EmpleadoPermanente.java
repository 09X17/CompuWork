package app.recursoshumanos.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("PERMANENTE")
@Getter
@Setter
public class EmpleadoPermanente extends Empleado {

    private double salarioMensual;
    private int aniosAntiguedad;

    @Override
    public String getTipo() {
        return "PERMANENTE";
    }
}
