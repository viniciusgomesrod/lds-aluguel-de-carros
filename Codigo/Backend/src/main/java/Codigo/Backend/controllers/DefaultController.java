package Codigo.Backend.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/default")
    public String defaultAfterLogin() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isCliente = auth.getAuthorities().stream()
                                .anyMatch(a -> a.getAuthority().equals("ROLE_CLIENTE"));
        if (isCliente) return "dashboardCliente";  // renderiza o template direto
        return "dashboardAgente";
    }
}
