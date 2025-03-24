package app.recursoshumanos.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TIEMPO_COMPLETO") 
public class EmpleadoTiempoCompleto extends Empleado {

    private double salarioMensual;

    public EmpleadoTiempoCompleto() {
    }

    public EmpleadoTiempoCompleto(String nombre, String correo, String cargo, double salarioMensual) {
        this.setNombre(nombre);
        this.setCorreo(correo);
        this.setCargo(cargo);
        this.salarioMensual = salarioMensual;
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(double salarioMensual) {
        this.salarioMensual = salarioMensual;
    }

    @Override
    public String getTipo() {
        return "TIEMPO_COMPLETO";
    }
}
