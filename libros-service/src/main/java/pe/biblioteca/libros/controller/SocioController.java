package pe.biblioteca.libros.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.biblioteca.libros.entity.Socio;
import pe.biblioteca.libros.service.SocioService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/socios")
@RequiredArgsConstructor
public class SocioController {

    private final SocioService socioService;

    @PostMapping
    public ResponseEntity<Socio> crear(@RequestBody Socio socio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(socioService.crear(socio));
    }

    @GetMapping
    public List<Socio> listar() {
        return socioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Socio> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(socioService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<Socio> buscarPorUsuario(@PathVariable String usuario) {
        return ResponseEntity.ok(socioService.buscarPorUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Socio> editar(@PathVariable Long id, @RequestBody Socio datos) {
        return ResponseEntity.ok(socioService.editar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        socioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
