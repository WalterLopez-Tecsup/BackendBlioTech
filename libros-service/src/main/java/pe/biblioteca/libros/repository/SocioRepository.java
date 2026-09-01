package pe.biblioteca.libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.biblioteca.libros.entity.Socio;

import java.util.Optional;

public interface SocioRepository extends JpaRepository<Socio, Long> {
    Optional<Socio> findByUsuario(String usuario);
}
