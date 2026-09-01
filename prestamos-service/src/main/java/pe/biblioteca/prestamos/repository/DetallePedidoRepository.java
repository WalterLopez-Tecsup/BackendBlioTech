package pe.biblioteca.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.biblioteca.prestamos.entity.DetallePedido;

import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
    List<DetallePedido> findByPedidoId(Long pedidoId);
}
