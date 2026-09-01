package pe.biblioteca.prestamos.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pe.biblioteca.prestamos.client.LibrosClient;
import pe.biblioteca.prestamos.dto.DetallePedidoRequest;
import pe.biblioteca.prestamos.dto.EjemplarResponse;
import pe.biblioteca.prestamos.dto.PedidoRequest;
import pe.biblioteca.prestamos.entity.DetallePedido;
import pe.biblioteca.prestamos.entity.Pedido;
import pe.biblioteca.prestamos.repository.DetallePedidoRepository;
import pe.biblioteca.prestamos.repository.PedidoRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final LibrosClient librosClient;

    public Pedido crear(PedidoRequest request) {
        double total = 0.0;

        for (DetallePedidoRequest item : request.items()) {
            EjemplarResponse ejemplar = librosClient.consultarEjemplar(item.ejemplarId());
            if (ejemplar == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Ejemplar no encontrado: " + item.ejemplarId());
            }
            double precio = ejemplar.getPrecio() != null ? ejemplar.getPrecio() : 0.0;
            total += precio * item.cantidad();
        }

        Pedido pedido = new Pedido(null, request.socioId(), null,
                request.nombre(), request.email(),
                request.direccion(), request.telefono(), total);
        Pedido guardado = pedidoRepository.save(pedido);

        for (DetallePedidoRequest item : request.items()) {
            EjemplarResponse ejemplar = librosClient.consultarEjemplar(item.ejemplarId());
            double precio = ejemplar != null && ejemplar.getPrecio() != null ? ejemplar.getPrecio() : 0.0;
            String titulo = ejemplar != null ? ejemplar.getTitulo() : "Desconocido";

            DetallePedido detalle = new DetallePedido(null, guardado.getId(),
                    item.ejemplarId(), titulo, precio, item.cantidad());
            detallePedidoRepository.save(detalle);
        }

        return guardado;
    }

    public List<Pedido> listarPorSocio(Long socioId) {
        return pedidoRepository.findBySocioId(socioId);
    }

    public List<DetallePedido> listarDetalle(Long pedidoId) {
        if (!pedidoRepository.existsById(pedidoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pedido no encontrado: " + pedidoId);
        }
        return detallePedidoRepository.findByPedidoId(pedidoId);
    }
}
