package pe.biblioteca.prestamos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.biblioteca.prestamos.dto.PedidoRequest;
import pe.biblioteca.prestamos.entity.DetallePedido;
import pe.biblioteca.prestamos.entity.Pedido;
import pe.biblioteca.prestamos.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody PedidoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.crear(request));
    }

    @GetMapping("/socio/{socioId}")
    public List<Pedido> listarPorSocio(@PathVariable Long socioId) {
        return pedidoService.listarPorSocio(socioId);
    }

    @GetMapping("/{pedidoId}/detalle")
    public List<DetallePedido> listarDetalle(@PathVariable Long pedidoId) {
        return pedidoService.listarDetalle(pedidoId);
    }
}
