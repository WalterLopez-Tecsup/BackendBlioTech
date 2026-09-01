package pe.biblioteca.prestamos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long socioId;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaPedido;

    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
    private Double totalPedido;
}
