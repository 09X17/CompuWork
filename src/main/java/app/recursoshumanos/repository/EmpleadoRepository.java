package app.recursoshumanos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.recursoshumanos.entity.Empleado;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByDepartamentoId(Long departamentoId);
}
