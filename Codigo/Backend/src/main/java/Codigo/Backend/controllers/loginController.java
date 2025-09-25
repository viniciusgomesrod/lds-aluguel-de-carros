package Codigo.Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Mudado de @Component para @Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Codigo.Backend.Services.loginService;
import Codigo.Backend.models.Agente;
import Codigo.Backend.models.Cliente;
import jakarta.servlet.http.HttpSession;

@Controller // Use @Controller para que o Spring reconheça os endpoints
public class loginController {

    @Autowired
    private loginService loginService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String senha,
                        HttpSession session,
                        Model model) {

        Object usuario = loginService.login(email, senha);

        if (usuario != null) {
            session.setAttribute("usuarioLogado", usuario);

            if (usuario instanceof Cliente) {
                return "redirect:/cliente/dashboard";
            } else if (usuario instanceof Agente) {
                return "redirect:/agente/dashboard";
            }
        }

        model.addAttribute("erro", "E-mail ou senha inválidos");
        return "login";
    }
}
