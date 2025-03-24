package app.recursoshumanos.service;

import app.recursoshumanos.entity.Departamento;
import app.recursoshumanos.entity.Empleado;
import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> listarEmpleados();
    Optional<Empleado> obtenerEmpleadoPorId(Long id);
    Empleado guardarEmpleado(Empleado empleado);
    void eliminarEmpleado(Long id);
    List<Empleado> listarPorDepartamento(Long departamentoId);
    List<Departamento> obtenerDepartamentos();
    Departamento buscarDepartamentoPorId(Long id);
}