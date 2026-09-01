package pe.biblioteca.prestamos.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;
    private Long ejemplarId;
    private String tituloEjemplar;
    private Double precio;
    private Integer cantidad;

    @JsonProperty("subtotal")
    public double getSubtotal() {
        return (precio != null ? precio : 0.0) * (cantidad != null ? cantidad : 0);
    }
}
