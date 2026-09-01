package pe.codigo.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.codigo.authservice.dto.SocioDto;

@Service
@RequiredArgsConstructor
public class SocioUserDetailsService implements UserDetailsService {

    private final RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            SocioDto socio = restTemplate.getForObject(
                    "http://libros-service/api/v1/socios/usuario/" + username,
                    SocioDto.class
            );
            if (socio == null) {
                throw new UsernameNotFoundException(username);
            }
            return User.builder()
                    .username(socio.usuario())
                    .password("{noop}" + socio.password())
                    .roles("SOCIO")
                    .build();
        } catch (UsernameNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UsernameNotFoundException("Socio no encontrado: " + username);
        }
    }
}
