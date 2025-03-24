package app.recursoshumanos.service;

import app.recursoshumanos.entity.ReporteDesempeno;

import java.util.List;
import java.util.Optional;

public interface ReporteDesempenoService {
    List<ReporteDesempeno> listarReportes();

    Optional<ReporteDesempeno> obtenerReportePorId(Long id);

    ReporteDesempeno guardarReporte(ReporteDesempeno reporte);

    void eliminarReporte(Long id);

    List<ReporteDesempeno> listarPorEmpleado(Long empleadoId);

    List<ReporteDesempeno> listarPorDepartamento(Long departamentoId);

    double promedioPuntuacionPorEmpleado(Long empleadoId);

    double promedioPuntuacionPorDepartamento(Long departamentoId);

    List<ReporteDesempeno> listarTodos();
}
