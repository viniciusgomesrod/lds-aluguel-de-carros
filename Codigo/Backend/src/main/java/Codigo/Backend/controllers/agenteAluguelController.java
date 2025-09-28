package Codigo.Backend.controllers;

import Codigo.Backend.Services.aluguelService;
import Codigo.Backend.Services.automovelService;
import Codigo.Backend.models.Aluguel;
import Codigo.Backend.models.Automovel;
import Codigo.Backend.models.StatusAluguel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/agente")
public class agenteAluguelController {

    @Autowired
    private aluguelService aluguelService;

    @Autowired
    private automovelService automovelService;

    // Listar todos os aluguéis
    @GetMapping("/alugueis")
    public String listarTodosAlugueis(Model model) {
        List<Aluguel> todosAlugueis = aluguelService.listarTodosAlugueis();
        model.addAttribute("alugueis", todosAlugueis);
        return "agente/listar-todos-alugueis";
    }

    // Listar aluguéis por status
    @GetMapping("/alugueis/status/{status}")
    public String listarAlugueisPorStatus(@PathVariable String status, Model model) {
        StatusAluguel statusEnum = StatusAluguel.valueOf(status.toUpperCase());
        List<Aluguel> alugueis = aluguelService.listarAlugueisPorStatus(statusEnum);
        model.addAttribute("alugueis", alugueis);
        model.addAttribute("statusFiltro", status);
        return "agente/listar-alugueis-filtrados";
    }

    // Detalhes de um aluguel específico
    @GetMapping("/alugueis/{id}")
    public String detalhesAluguel(@PathVariable Long id, Model model) {
        Aluguel aluguel = aluguelService.obterAluguelPorId(id);
        model.addAttribute("aluguel", aluguel);
        return "agente/detalhes-aluguel";
    }

    // Aprovar aluguel
    @PostMapping("/alugueis/{id}/aprovar")
    public String aprovarAluguel(@PathVariable Long id) {
        try {
            aluguelService.aprovarAluguel(id);
            return "redirect:/agente/alugueis?success=Aluguel+aprovado+com+sucesso";
        } catch (Exception e) {
            return "redirect:/agente/alugueis?error=Erro+ao+aprovar+aluguel:+" + e.getMessage();
        }
    }

    // Rejeitar aluguel
    @PostMapping("/alugueis/{id}/rejeitar")
    public String rejeitarAluguel(@PathVariable Long id, @RequestParam String motivo) {
        try {
            aluguelService.rejeitarAluguel(id, motivo);
            return "redirect:/agente/alugueis?success=Aluguel+rejeitado+com+sucesso";
        } catch (Exception e) {
            return "redirect:/agente/alugueis?error=Erro+ao+rejeitar+aluguel:+" + e.getMessage();
        }
    }

    // Análise financeira
    @GetMapping("/alugueis/{id}/analise-financeira")
    public String analiseFinanceira(@PathVariable Long id, Model model) {
        Aluguel aluguel = aluguelService.obterAluguelPorId(id);
        model.addAttribute("aluguel", aluguel);
        return "agente/analise-financeira";
    }
}
