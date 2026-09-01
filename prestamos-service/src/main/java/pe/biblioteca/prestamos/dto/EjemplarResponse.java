package pe.biblioteca.prestamos.dto;

import lombok.Data;

@Data
public class EjemplarResponse {
    private Long id;
    private String titulo;
    private String autor;
    private boolean disponible;
    private Double precio;
}
