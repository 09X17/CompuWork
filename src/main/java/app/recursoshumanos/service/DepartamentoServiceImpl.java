package app.recursoshumanos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.recursoshumanos.entity.Departamento;
import app.recursoshumanos.exception.RecursoNoEncontradoException;
import app.recursoshumanos.repository.DepartamentoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public List<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }

    @Override
    public Optional<Departamento> obtenerDepartamentoPorId(Long id) {
        return departamentoRepository.findById(id);
    }

    @Override
    public Departamento guardarDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @Override
    public void eliminarDepartamento(Long id) {
        if (!departamentoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Departamento no encontrado con ID: " + id);
        }
        departamentoRepository.deleteById(id);
    }
}