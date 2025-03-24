package app.recursoshumanos.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ReporteDesempeno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La evaluación no puede ser nula.")
    private String evaluacion;

    @Min(value = 1, message = "La puntuación debe ser al menos 1.")
    @Max(value = 10, message = "La puntuación no puede ser mayor a 10.")
    private int puntuacion;

    @NotNull(message = "La fecha no puede ser nula.")
    @FutureOrPresent(message = "La fecha debe ser actual o futura.")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @Column(nullable = true, length = 1000)
    private String comentarios;

    @PrePersist
    public void asignarFecha() {
        if (this.fecha == null) {
            this.fecha = LocalDate.now();
        }
    }

}
