package pe.biblioteca.prestamos.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import pe.biblioteca.prestamos.dto.EjemplarResponse;
import pe.biblioteca.prestamos.dto.SocioResponse;

import java.util.Map;

@Slf4j
@Component
public class LibrosClient {

    private final RestClient restClient;

    public LibrosClient(RestClient.Builder builder) {
        this.restClient = builder.clone()
                .baseUrl("http://libros-service")
                .build();
    }

    public SocioResponse consultarSocio(Long socioId) {
        try {
            return restClient.get()
                    .uri("/api/v1/socios/{id}", socioId)
                    .retrieve()
                    .body(SocioResponse.class);
        } catch (Exception e) {
            log.warn("No se pudo consultar socio {}: {}", socioId, e.getMessage());
            return null;
        }
    }

    public EjemplarResponse consultarEjemplar(Long ejemplarId) {
        try {
            return restClient.get()
                    .uri("/api/v1/libros/{id}", ejemplarId)
                    .retrieve()
                    .body(EjemplarResponse.class);
        } catch (Exception e) {
            log.warn("No se pudo consultar ejemplar {}: {}", ejemplarId, e.getMessage());
            return null;
        }
    }

    public void cambiarDisponibilidad(Long ejemplarId, boolean disponible) {
        try {
            restClient.patch()
                    .uri("/api/v1/libros/{id}/disponibilidad", ejemplarId)
                    .body(Map.of("disponible", disponible))
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            log.error("Error al cambiar disponibilidad del ejemplar {}: {}", ejemplarId, e.getMessage());
        }
    }
}
