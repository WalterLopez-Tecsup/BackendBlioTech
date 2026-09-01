package pe.biblioteca.prestamos.dto;

import java.util.List;

public record PedidoRequest(
        Long socioId,
        String nombre,
        String email,
        String direccion,
        String telefono,
        List<DetallePedidoRequest> items
) {}
