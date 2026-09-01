package pe.codigo.authservice.dto;

public record SocioDto(
        Long id,
        String nombre,
        String usuario,
        String email,
        String telefono,
        String fechaInscripcion,
        boolean activo,
        String password,
        String fechaCreacion,
        String fechaActualizacion
) {}
