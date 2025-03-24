package app.recursoshumanos.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("POR_HORAS") 
public class EmpleadoPorHoras extends Empleado {

    private double tarifaHora;
    private int horasTrabajadas;

    public EmpleadoPorHoras() {
    }

    public EmpleadoPorHoras(String nombre, String correo, String cargo, double tarifaHora, int horasTrabajadas) {
        this.setNombre(nombre);
        this.setCorreo(correo);
        this.setCargo(cargo);
        this.tarifaHora = tarifaHora;
        this.horasTrabajadas = horasTrabajadas;
    }

    public double getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(double tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public String getTipo() {
        return "POR_HORAS";
    }
}
