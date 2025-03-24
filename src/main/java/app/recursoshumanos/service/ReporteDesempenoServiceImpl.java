package app.recursoshumanos.service;

import app.recursoshumanos.entity.ReporteDesempeno;
import app.recursoshumanos.repository.ReporteDesempenoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteDesempenoServiceImpl implements ReporteDesempenoService {

    @Autowired
    private ReporteDesempenoRepository reporteDesempenoRepository;

    @Override
    public List<ReporteDesempeno> listarReportes() {
        return reporteDesempenoRepository.findAll();
    }

    @Override
    public Optional<ReporteDesempeno> obtenerReportePorId(Long id) {
        return reporteDesempenoRepository.findById(id);
    }

    @Override
    public ReporteDesempeno guardarReporte(ReporteDesempeno reporte) {
        return reporteDesempenoRepository.save(reporte);
    }

    @Override
    public void eliminarReporte(Long id) {
        reporteDesempenoRepository.deleteById(id);
    }

    @Override
    public List<ReporteDesempeno> listarPorEmpleado(Long empleadoId) {
        return reporteDesempenoRepository.findByEmpleadoId(empleadoId);
    }

    @Override
    public List<ReporteDesempeno> listarPorDepartamento(Long departamentoId) {
        return reporteDesempenoRepository.findByEmpleadoDepartamentoId(departamentoId);
    }

    @Override
    public double promedioPuntuacionPorEmpleado(Long empleadoId) {
        List<ReporteDesempeno> reportes = listarPorEmpleado(empleadoId);
        if (reportes.isEmpty()) return 0;
        return reportes.stream().mapToDouble(ReporteDesempeno::getPuntuacion).average().orElse(0);
    }

    @Override
    public double promedioPuntuacionPorDepartamento(Long departamentoId) {
        List<ReporteDesempeno> reportes = listarPorDepartamento(departamentoId);
        if (reportes.isEmpty()) return 0;
        return reportes.stream().mapToDouble(ReporteDesempeno::getPuntuacion).average().orElse(0);
    }

    @Override
    public List<ReporteDesempeno> listarTodos() {
        return reporteDesempenoRepository.findAll();
    }
}
