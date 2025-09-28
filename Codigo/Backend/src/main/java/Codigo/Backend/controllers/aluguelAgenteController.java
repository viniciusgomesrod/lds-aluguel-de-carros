package Codigo.Backend.controllers;

import Codigo.Backend.Services.aluguelService;
import Codigo.Backend.models.Aluguel;
import Codigo.Backend.models.StatusAluguel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/agente/alugueis")
public class aluguelAgenteController {

    @Autowired
    private aluguelService aluguelService;

    // Listar todos os aluguéis para análise
    @GetMapping
    public String listarAlugueis(Model model) {
        List<Aluguel> todosAlugueis = aluguelService.listarTodosAlugueis();
        List<Aluguel> alugueisPendentes = aluguelService.listarAlugueisPorStatus(StatusAluguel.PENDENTE);
        List<Aluguel> alugueisAprovados = aluguelService.listarAlugueisPorStatus(StatusAluguel.APROVADO);
        List<Aluguel> alugueisNegados = aluguelService.listarAlugueisPorStatus(StatusAluguel.NEGADO);

        model.addAttribute("todosAlugueis", todosAlugueis);
        model.addAttribute("alugueisPendentes", alugueisPendentes);
        model.addAttribute("alugueisAprovados", alugueisAprovados);
        model.addAttribute("alugueisNegados", alugueisNegados);
        
        return "agente/listar-alugueis";
    }

    // Visualizar detalhes de um aluguel específico
    @GetMapping("/{id}")
    public String visualizarAluguel(@PathVariable Long id, Model model) {
        Aluguel aluguel = aluguelService.obterAluguelPorId(id);
        model.addAttribute("aluguel", aluguel);
        return "agente/detalhes-aluguel";
    }

    // Aprovar aluguel
    @PostMapping("/{id}/aprovar")
    public String aprovarAluguel(@PathVariable Long id) {
        try {
            aluguelService.aprovarAluguel(id);
            return "redirect:/agente/alugueis?success=Aluguel+aprovado+com+sucesso";
        } catch (Exception e) {
            return "redirect:/agente/alugueis?error=Erro+ao+aprovar+aluguel:+" + e.getMessage();
        }
    }

    // Rejeitar aluguel
    @PostMapping("/{id}/rejeitar")
    public String rejeitarAluguel(@PathVariable Long id, @RequestParam(required = false) String motivo) {
        try {
            aluguelService.rejeitarAluguel(id, motivo);
            return "redirect:/agente/alugueis?success=Aluguel+rejeitado+com+sucesso";
        } catch (Exception e) {
            return "redirect:/agente/alugueis?error=Erro+ao+rejeitar+aluguel:+" + e.getMessage();
        }
    }

    // Análise financeira do cliente
    @GetMapping("/{id}/analise-financeira")
    public String analiseFinanceira(@PathVariable Long id, Model model) {
        Aluguel aluguel = aluguelService.obterAluguelPorId(id);
        model.addAttribute("aluguel", aluguel);
        return "agente/analise-financeira";
    }

    // Processar análise financeira
    @PostMapping("/{id}/processar-analise")
    public String processarAnalise(@PathVariable Long id, 
                                 @RequestParam String decisao,
                                 @RequestParam(required = false) String observacoes) {
        try {
            if ("aprovar".equals(decisao)) {
                aluguelService.aprovarAluguel(id);
                return "redirect:/agente/alugueis?success=Aluguel+aprovado+após+análise+financeira";
            } else {
                aluguelService.rejeitarAluguel(id, observacoes);
                return "redirect:/agente/alugueis?success=Aluguel+rejeitado+após+análise+financeira";
            }
        } catch (Exception e) {
            return "redirect:/agente/alugueis?error=Erro+ao+processar+análise:+" + e.getMessage();
        }
    }
}
