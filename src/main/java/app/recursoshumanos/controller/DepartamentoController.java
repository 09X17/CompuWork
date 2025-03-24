package app.recursoshumanos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.recursoshumanos.entity.Departamento;
import app.recursoshumanos.service.DepartamentoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public String listarDepartamentos(Model model) {
        model.addAttribute("departamentos", departamentoService.listarDepartamentos());
        return "departamentos/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("departamento", new Departamento());
        return "departamentos/crear";
    }

    @PostMapping("/guardar")
    public String guardarDepartamento(
            @Valid @ModelAttribute Departamento departamento,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "departamentos/crear";
        }

        departamentoService.guardarDepartamento(departamento);
        return "redirect:/departamentos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Departamento departamento = departamentoService.obtenerDepartamentoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));

        model.addAttribute("departamento", departamento);
        return "departamentos/crear";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarDepartamento(
            @PathVariable Long id,
            @Valid @ModelAttribute Departamento departamento,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("departamento", departamento);
            return "departamentos/crear";
        }

        departamento.setId(id);
        departamentoService.guardarDepartamento(departamento);
        return "redirect:/departamentos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarDepartamento(@PathVariable Long id) {
        departamentoService.eliminarDepartamento(id);
        return "redirect:/departamentos";
    }
}
