package app.recursoshumanos.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import app.recursoshumanos.entity.Departamento;
import app.recursoshumanos.exception.RecursoNoEncontradoException;
import app.recursoshumanos.repository.DepartamentoRepository;

@ExtendWith(MockitoExtension.class)
class DepartamentoServiceImplTest {

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private DepartamentoServiceImpl departamentoService;

    @Test
    void testListarDepartamentos() {
        Departamento d1 = new Departamento();
        Departamento d2 = new Departamento();
        when(departamentoRepository.findAll()).thenReturn(Arrays.asList(d1, d2));

        List<Departamento> departamentos = departamentoService.listarDepartamentos();

        assertEquals(2, departamentos.size());
        verify(departamentoRepository).findAll();
    }

    @Test
    void testObtenerDepartamentoPorId_Existe() {
        Departamento departamento = new Departamento();
        when(departamentoRepository.findById(1L)).thenReturn(Optional.of(departamento));

        Optional<Departamento> resultado = departamentoService.obtenerDepartamentoPorId(1L);

        assertTrue(resultado.isPresent());
        verify(departamentoRepository).findById(1L);
    }

    @Test
    void testGuardarDepartamento() {
        Departamento departamento = new Departamento();
        when(departamentoRepository.save(departamento)).thenReturn(departamento);

        Departamento guardado = departamentoService.guardarDepartamento(departamento);

        assertNotNull(guardado);
        verify(departamentoRepository).save(departamento);
    }

    @Test
    void testEliminarDepartamento_NoExiste() {
        when(departamentoRepository.existsById(10L)).thenReturn(false);

        Exception ex = assertThrows(RecursoNoEncontradoException.class, () ->
                departamentoService.eliminarDepartamento(10L));

        assertEquals("Departamento no encontrado con ID: 10", ex.getMessage());
        verify(departamentoRepository, never()).deleteById(anyLong());
    }

    @Test
    void testEliminarDepartamento_Existe() {
        when(departamentoRepository.existsById(5L)).thenReturn(true);

        departamentoService.eliminarDepartamento(5L);

        verify(departamentoRepository).deleteById(5L);
    }
}
