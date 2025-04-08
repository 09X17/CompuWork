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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import app.recursoshumanos.entity.Empleado;
import app.recursoshumanos.exception.RecursoNoEncontradoException;
import app.recursoshumanos.repository.DepartamentoRepository;
import app.recursoshumanos.repository.EmpleadoRepository;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceImplTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    @Test
    void testListarEmpleados() {
        Empleado e1 = new Empleado();
        Empleado e2 = new Empleado();
        when(empleadoRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Empleado> empleados = empleadoService.listarEmpleados();

        assertEquals(2, empleados.size());
        verify(empleadoRepository, times(1)).findAll();
    }

    @Test
    void testObtenerEmpleadoPorId_Existe() {
        Empleado empleado = new Empleado();
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

        Optional<Empleado> resultado = empleadoService.obtenerEmpleadoPorId(1L);

        assertTrue(resultado.isPresent());
        verify(empleadoRepository).findById(1L);
    }

    @Test
    void testEliminarEmpleado_NoExiste() {
        when(empleadoRepository.existsById(99L)).thenReturn(false);

        Exception ex = assertThrows(RecursoNoEncontradoException.class, () ->
                empleadoService.eliminarEmpleado(99L));

        assertEquals("Empleado no encontrado con ID: 99", ex.getMessage());
        verify(empleadoRepository, never()).deleteById(anyLong());
    }

    @Test
    void testGuardarEmpleado() {
        Empleado empleado = new Empleado();
        when(empleadoRepository.save(empleado)).thenReturn(empleado);

        Empleado guardado = empleadoService.guardarEmpleado(empleado);

        assertNotNull(guardado);
        verify(empleadoRepository).save(empleado);
    }

    @Test
    void testBuscarDepartamentoPorId_NoExiste() {
        when(departamentoRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                empleadoService.buscarDepartamentoPorId(999L));
    }
}
