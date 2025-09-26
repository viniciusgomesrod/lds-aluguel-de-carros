package Codigo.Backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class dashboardController {
     @GetMapping("/dashboardCliente")
    public String dashboardCliente() {
        return "dashboardCliente"; // templates/cliente/dashboardCliente.html
    }

    @GetMapping("/dashboardAgente")
    public String dashboardAgente() {
        return "dashboardAgente"; // templates/agente/dashboardAgente.html
    }
}
