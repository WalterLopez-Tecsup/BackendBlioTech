package pe.biblioteca.libros.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.biblioteca.libros.entity.Ejemplar;
import pe.biblioteca.libros.service.EjemplarService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/libros")
@RequiredArgsConstructor
public class EjemplarController {

    private final EjemplarService ejemplarService;

    @PostMapping
    public ResponseEntity<Ejemplar> crear(@RequestBody Ejemplar ejemplar) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ejemplarService.crear(ejemplar));
    }

    @GetMapping
    public List<Ejemplar> listar() {
        return ejemplarService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ejemplar> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(ejemplarService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ejemplar> editar(@PathVariable Long id, @RequestBody Ejemplar datos) {
        return ResponseEntity.ok(ejemplarService.editar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ejemplarService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/disponibilidad")
    public ResponseEntity<Ejemplar> cambiarDisponibilidad(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> body) {
        return ResponseEntity.ok(ejemplarService.cambiarDisponibilidad(id, body.get("disponible")));
    }
}
