package pe.biblioteca.prestamos.dto;

import lombok.Data;

@Data
public class PrestamoRequest {
    private Long ejemplarId;
    private Long socioId;
    private Integer diasPrestamo;
}
