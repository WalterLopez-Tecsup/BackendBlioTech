package pe.codigo.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pe.codigo.authservice.dto.LoginRequest;
import pe.codigo.authservice.dto.LoginResponse;
import pe.codigo.authservice.dto.SocioDto;
import pe.codigo.authservice.dto.ValidarTokenRequest;
import pe.codigo.authservice.security.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final RestTemplate restTemplate;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        SocioDto socio;
        try {
            socio = restTemplate.getForObject(
                    "http://libros-service/api/v1/socios/usuario/" + request.usuario(),
                    SocioDto.class
            );
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales inválidas"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(Map.of("error", "Servicio no disponible"));
        }

        if (socio == null || !socio.password().equals(request.clave())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales inválidas"));
        }

        if (!socio.activo()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Socio inactivo"));
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(socio.usuario());
        String token = jwtService.generarToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(
                token, "Bearer",
                socio.id(), socio.nombre(), socio.usuario(),
                socio.email(), socio.telefono(), socio.activo()
        ));
    }

    @PostMapping("/validar")
    public ResponseEntity<Map<String, Object>> validarToken(@RequestBody ValidarTokenRequest request) {
        try {
            String username = jwtService.extraerUsername(request.token());
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            boolean valido = jwtService.esValido(request.token(), userDetails);
            return ResponseEntity.ok(Map.of("valido", valido, "usuario", username));
        } catch (Exception e) {
            return ResponseEntity.ok(Map.of("valido", false));
        }
    }
}
