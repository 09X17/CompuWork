package app.recursoshumanos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ManejoGlobalExcepciones {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Object> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("timestamp", LocalDateTime.now());
        cuerpo.put("mensaje", ex.getMessage());

        return new ResponseEntity<>(cuerpo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> manejarExcepcionGeneral(Exception ex) {
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("timestamp", LocalDateTime.now());
        cuerpo.put("mensaje", "Error interno del servidor: " + ex.getMessage());

        return new ResponseEntity<>(cuerpo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
