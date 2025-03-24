package app.recursoshumanos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.recursoshumanos.entity.Empleado;
import app.recursoshumanos.entity.EmpleadoPermanente;
import app.recursoshumanos.entity.EmpleadoPorHoras;
import app.recursoshumanos.entity.EmpleadoTemporal;
import app.recursoshumanos.entity.EmpleadoTiempoCompleto;
import app.recursoshumanos.service.EmpleadoService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public String listarEmpleados(Model model) {
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        return "empleados/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("departamentos", empleadoService.obtenerDepartamentos());
        return "empleados/crear";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(
            @RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String cargo,
            @RequestParam Long departamentoId,
            @RequestParam String tipo,
            @RequestParam(required = false) Double salarioMensual,
            @RequestParam(required = false) Double salarioPorHora,
            @RequestParam(required = false) Double salarioFijo,
            @RequestParam(required = false) Double pagoPorHora,
            @RequestParam(required = false) Integer horasTrabajadas
    ) {
        Empleado empleado;

        switch (tipo) {
            case "TIEMPO_COMPLETO":
                EmpleadoTiempoCompleto tc = new EmpleadoTiempoCompleto();
                if (salarioMensual != null) {
                    tc.setSalarioMensual(salarioMensual);
                }
                empleado = tc;
                break;

            case "TEMPORAL":
                EmpleadoTemporal temp = new EmpleadoTemporal();
                if (pagoPorHora != null) {
                    temp.setPagoPorHora(pagoPorHora);
                }
                if (horasTrabajadas != null) {
                    temp.setHorasTrabajadas(horasTrabajadas);
                }
                empleado = temp;
                break;

            case "HORAS":
                EmpleadoPorHoras ph = new EmpleadoPorHoras();
                if (salarioPorHora != null) {
                    ph.setTarifaHora(salarioPorHora);
                }
                empleado = ph;
                break;

            case "PERMANENTE":
                EmpleadoPermanente pm = new EmpleadoPermanente();
                if (salarioFijo != null) {
                    pm.setSalarioMensual(salarioFijo);
                }
                empleado = pm;
                break;

            default:
                throw new IllegalArgumentException("Tipo de empleado no válido");
        }

        empleado.setNombre(nombre);
        empleado.setCorreo(correo);
        empleado.setCargo(cargo);
        empleado.setDepartamento(empleadoService.buscarDepartamentoPorId(departamentoId));

        empleadoService.guardarEmpleado(empleado);
        return "redirect:/empleados?guardado";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable Long id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/empleados?eliminado";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Empleado empleado = empleadoService.obtenerEmpleadoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado con id: " + id));

        model.addAttribute("empleado", empleado);
        model.addAttribute("departamentos", empleadoService.obtenerDepartamentos());
        return "empleados/editar";
    }

    @PostMapping("/actualizar")
    public String actualizarEmpleado(
            @RequestParam Long id,
            @RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String cargo,
            @RequestParam Long departamentoId,
            @RequestParam String tipo,
            @RequestParam(required = false) Double salarioMensual,
            @RequestParam(required = false) Double salarioPorHora,
            @RequestParam(required = false) Double salarioFijo,
            @RequestParam(required = false) Double pagoPorHora,
            @RequestParam(required = false) Integer horasTrabajadas
    ) {
        Empleado empleadoExistente = empleadoService.obtenerEmpleadoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        empleadoExistente.setNombre(nombre);
        empleadoExistente.setCorreo(correo);
        empleadoExistente.setCargo(cargo);
        empleadoExistente.setDepartamento(empleadoService.buscarDepartamentoPorId(departamentoId));

        switch (tipo) {
            case "TIEMPO_COMPLETO":
                if (empleadoExistente instanceof EmpleadoTiempoCompleto tc && salarioMensual != null) {
                    tc.setSalarioMensual(salarioMensual);
                }
                break;

            case "TEMPORAL":
                if (empleadoExistente instanceof EmpleadoTemporal temp) {
                    if (pagoPorHora != null) {
                        temp.setPagoPorHora(pagoPorHora);
                    }
                    if (horasTrabajadas != null) {
                        temp.setHorasTrabajadas(horasTrabajadas);
                    }
                }
                break;

            case "HORAS":
                if (empleadoExistente instanceof EmpleadoPorHoras ph && salarioPorHora != null) {
                    ph.setTarifaHora(salarioPorHora);
                }
                break;

            case "PERMANENTE":
                if (empleadoExistente instanceof EmpleadoPermanente pm && salarioFijo != null) {
                    pm.setSalarioMensual(salarioFijo);
                }
                break;
        }

        empleadoService.guardarEmpleado(empleadoExistente);
        return "redirect:/empleados?actualizado";
    }

}
