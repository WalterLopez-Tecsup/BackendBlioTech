package pe.codigo.authservice.dto;

public record LoginResponse(
        String token,
        String tipo,
        Long id,
        String nombre,
        String usuario,
        String email,
        String telefono,
        boolean activo
) {}
