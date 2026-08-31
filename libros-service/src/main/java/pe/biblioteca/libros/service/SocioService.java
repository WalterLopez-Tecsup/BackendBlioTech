package pe.biblioteca.libros.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.biblioteca.libros.entity.Socio;
import pe.biblioteca.libros.exception.SocioNoEncontradoException;
import pe.biblioteca.libros.repository.SocioRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocioService {

    private final SocioRepository socioRepository;

    public List<Socio> listar() {
        return socioRepository.findAll();
    }

    public Socio buscarPorId(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new SocioNoEncontradoException(String.valueOf(id)));
    }

    public Socio crear(Socio socio) {
        return socioRepository.save(socio);
    }

    public Socio editar(Long id, Socio datos) {
        Socio existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setEmail(datos.getEmail());
        existente.setTelefono(datos.getTelefono());
        existente.setActivo(datos.isActivo());
        return socioRepository.save(existente);
    }

    public void eliminar(Long id) {
        buscarPorId(id);
        socioRepository.deleteById(id);
    }
}
