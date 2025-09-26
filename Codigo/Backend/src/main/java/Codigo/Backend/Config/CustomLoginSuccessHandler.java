package Codigo.Backend.Config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // Verifica a role do usuário logado
        boolean isCliente = false;
        boolean isAgente = false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (auth.getAuthority().equals("ROLE_CLIENTE")) {
                isCliente = true;
                break;
            } else if (auth.getAuthority().equals("ROLE_AGENTE")) {
                isAgente = true;
                break;
            }
        }

        if (isCliente) {
            response.sendRedirect("/dashboardCliente");
        } else if (isAgente) {
            response.sendRedirect("/dashboardAgente");
        } else {
            response.sendRedirect("/login"); // fallback
        }
    }
}
