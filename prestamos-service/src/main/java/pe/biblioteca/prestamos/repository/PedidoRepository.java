package pe.biblioteca.prestamos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.biblioteca.prestamos.entity.Pedido;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findBySocioId(Long socioId);
}
