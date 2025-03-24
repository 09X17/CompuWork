package app.recursoshumanos.service;

import java.util.List;
import java.util.Optional;

import app.recursoshumanos.entity.Departamento;

public interface DepartamentoService {
    List<Departamento> listarDepartamentos(); 
    Optional<Departamento> obtenerDepartamentoPorId(Long id);
    Departamento guardarDepartamento(Departamento departamento); 
    void eliminarDepartamento(Long id); 
}