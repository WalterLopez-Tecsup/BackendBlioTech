package pe.biblioteca.prestamos.dto;

import lombok.Data;

@Data
public class SocioResponse {
    private Long id;
    private String nombre;
    private String email;
    private boolean activo;
}
