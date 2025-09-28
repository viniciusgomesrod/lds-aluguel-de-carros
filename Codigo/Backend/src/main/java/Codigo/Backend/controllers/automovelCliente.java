package Codigo.Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Codigo.Backend.Services.automovelService;

@Controller
@RequestMapping("/cliente/automoveis")
public class automovelCliente {
    @Autowired
    private automovelService automovelService;

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("automoveis", automovelService.listarTodosAutomoveis());
        return "automoveis/lista";
    }

    @GetMapping("/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        model.addAttribute("automovel", automovelService.obterAutomovelPorId(id));
        return "automoveis/detalhe"; 
    }
}