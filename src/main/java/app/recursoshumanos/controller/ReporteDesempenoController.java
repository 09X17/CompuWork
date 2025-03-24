package app.recursoshumanos.controller;

import app.recursoshumanos.entity.Empleado;
import app.recursoshumanos.entity.ReporteDesempeno;
import app.recursoshumanos.service.EmpleadoService;
import app.recursoshumanos.service.ReporteDesempenoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reportes")
public class ReporteDesempenoController {

    @Autowired
    private ReporteDesempenoService reporteDesempenoService;
    
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public String listarReportes(Model model) {
        model.addAttribute("reportes", reporteDesempenoService.listarTodos());
        return "reportes/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        model.addAttribute("tiposEvaluacion", List.of("EXCELENTE", "BUENA", "REGULAR", "MALA"));
        return "reportes/crear";
    }

    @PostMapping("/guardar")
    public String guardarReporte(
            @RequestParam Long empleadoId,
            @RequestParam String fecha,
            @RequestParam String evaluacion,
            @RequestParam int puntuacion,
            @RequestParam(required = false) String comentarios,
            RedirectAttributes redirectAttributes) {
        
        try {
            LocalDate fechaReporte = LocalDate.parse(fecha);
            Empleado empleado = empleadoService.obtenerEmpleadoPorId(empleadoId)
                    .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
            
            ReporteDesempeno reporte = new ReporteDesempeno();
            reporte.setEmpleado(empleado);
            reporte.setFecha(fechaReporte);
            reporte.setEvaluacion(evaluacion);
            reporte.setPuntuacion(puntuacion);
            reporte.setComentarios(comentarios);
            
            reporteDesempenoService.guardarReporte(reporte);
            
            redirectAttributes.addFlashAttribute("success", "Reporte guardado exitosamente");
            return "redirect:/reportes";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
            return "redirect:/reportes/crear";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        ReporteDesempeno reporte = reporteDesempenoService.obtenerReportePorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Reporte no encontrado"));
        
        model.addAttribute("reporte", reporte);
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        model.addAttribute("tiposEvaluacion", List.of("EXCELENTE", "BUENA", "REGULAR", "MALA"));
        return "reportes/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarReporte(
            @PathVariable Long id,
            @RequestParam Long empleadoId,
            @RequestParam String fecha,
            @RequestParam String evaluacion,
            @RequestParam int puntuacion,
            @RequestParam(required = false) String comentarios,
            RedirectAttributes redirectAttributes) {
        
        try {
            LocalDate fechaReporte = LocalDate.parse(fecha);
            Empleado empleado = empleadoService.obtenerEmpleadoPorId(empleadoId)
                    .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
            
            ReporteDesempeno reporte = reporteDesempenoService.obtenerReportePorId(id)
                    .orElseThrow(() -> new IllegalArgumentException("Reporte no encontrado"));
            
            reporte.setEmpleado(empleado);
            reporte.setFecha(fechaReporte);
            reporte.setEvaluacion(evaluacion);
            reporte.setPuntuacion(puntuacion);
            reporte.setComentarios(comentarios);
            
            reporteDesempenoService.guardarReporte(reporte);
            
            redirectAttributes.addFlashAttribute("success", "Reporte actualizado exitosamente");
            return "redirect:/reportes";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar: " + e.getMessage());
            return "redirect:/reportes/editar/" + id;
        }
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarReporte(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            reporteDesempenoService.eliminarReporte(id);
            redirectAttributes.addFlashAttribute("success", "Reporte eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar: " + e.getMessage());
        }
        return "redirect:/reportes";
    }
}