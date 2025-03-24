package app.recursoshumanos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.recursoshumanos.entity.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
