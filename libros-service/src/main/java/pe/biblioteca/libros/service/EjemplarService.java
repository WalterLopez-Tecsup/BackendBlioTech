package pe.biblioteca.libros.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.biblioteca.libros.entity.Ejemplar;
import pe.biblioteca.libros.exception.EjemplarNoEncontradoException;
import pe.biblioteca.libros.repository.EjemplarRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EjemplarService {

    private final EjemplarRepository ejemplarRepository;

    public List<Ejemplar> listar() {
        return ejemplarRepository.findAll();
    }

    public Ejemplar buscarPorId(Long id) {
        return ejemplarRepository.findById(id)
                .orElseThrow(() -> new EjemplarNoEncontradoException(String.valueOf(id)));
    }

    public Ejemplar crear(Ejemplar ejemplar) {
        return ejemplarRepository.save(ejemplar);
    }

    public Ejemplar editar(Long id, Ejemplar datos) {
        Ejemplar existente = buscarPorId(id);
        existente.setTitulo(datos.getTitulo());
        existente.setAutor(datos.getAutor());
        existente.setIsbn(datos.getIsbn());
        existente.setAnioPublicacion(datos.getAnioPublicacion());
        existente.setDisponible(datos.isDisponible());
        return ejemplarRepository.save(existente);
    }

    public void eliminar(Long id) {
        buscarPorId(id);
        ejemplarRepository.deleteById(id);
    }

    public Ejemplar cambiarDisponibilidad(Long id, boolean disponible) {
        Ejemplar ejemplar = buscarPorId(id);
        ejemplar.setDisponible(disponible);
        return ejemplarRepository.save(ejemplar);
    }
}
