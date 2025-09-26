package Codigo.Backend.controllers;

import Codigo.Backend.Services.loginService;
import Codigo.Backend.models.Agente;
import Codigo.Backend.models.Cliente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class loginController {

    @Autowired
    private loginService loginService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
