package app.recursoshumanos.service;

import app.recursoshumanos.entity.Departamento;
import app.recursoshumanos.entity.Empleado;
import app.recursoshumanos.exception.RecursoNoEncontradoException;
import app.recursoshumanos.repository.DepartamentoRepository;
import app.recursoshumanos.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> obtenerEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Empleado no encontrado con ID: " + id);
        }
        empleadoRepository.deleteById(id);
    }

    @Override
    public List<Empleado> listarPorDepartamento(Long departamentoId) {
        return empleadoRepository.findByDepartamentoId(departamentoId);
    }

    @Override
    public List<Departamento> obtenerDepartamentos() {
        return departamentoRepository.findAll();
    }

    @Override
    public Departamento buscarDepartamentoPorId(Long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado con ID: " + id));
    }
}