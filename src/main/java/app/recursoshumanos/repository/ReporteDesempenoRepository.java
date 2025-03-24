package app.recursoshumanos.repository;

import app.recursoshumanos.entity.ReporteDesempeno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReporteDesempenoRepository extends JpaRepository<ReporteDesempeno, Long> {
    List<ReporteDesempeno> findByEmpleadoId(Long empleadoId);
    List<ReporteDesempeno> findByEmpleadoDepartamentoId(Long departamentoId);
}
