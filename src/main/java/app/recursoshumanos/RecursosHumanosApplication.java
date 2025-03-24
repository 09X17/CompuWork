package app.recursoshumanos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import app.recursoshumanos.entity.Departamento;
import app.recursoshumanos.repository.DepartamentoRepository;

@SpringBootApplication
public class RecursosHumanosApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecursosHumanosApplication.class, args);
    }

    @Bean
    public CommandLineRunner cargarDepartamentos(DepartamentoRepository departamentoRepository) {
        return args -> {
            if (departamentoRepository.count() == 0) {
                departamentoRepository.save(new Departamento("Recursos Humanos"));
                departamentoRepository.save(new Departamento("Tecnología"));
                departamentoRepository.save(new Departamento("Finanzas"));
                departamentoRepository.save(new Departamento("Marketing"));
                System.out.println("Departamentos creados ✔️");
            }
        };
    }
}
