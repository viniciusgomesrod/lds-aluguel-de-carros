package Codigo.Backend.controllers;

import Codigo.Backend.Services.aluguelService;
import Codigo.Backend.Services.automovelService;
import Codigo.Backend.Services.clienteService;
import Codigo.Backend.models.Aluguel;
import Codigo.Backend.models.Automovel;
import Codigo.Backend.models.Cliente;
import Codigo.Backend.models.StatusAluguel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/alugueis")
public class aluguelController {

    @Autowired
    private aluguelService aluguelService;

    @Autowired
    private automovelService automovelService;

    @Autowired
    private clienteService clienteService;

    @GetMapping
    public String listarAlugueis(Model model) {
        List<Aluguel> alugueis = aluguelService.listarTodosAlugueis();
        model.addAttribute("alugueis", alugueis);
        return "aluguel/listar-alugueis";
    }

    @GetMapping("/criar")
    public String formularioAluguel(Model model) {
        model.addAttribute("aluguel", new Aluguel());
        model.addAttribute("automoveis", automovelService.listarAutomoveisDisponiveis());
        return "aluguel/formulario-aluguel";
    }

    @PostMapping("/criar")
    public String criarAluguel(@ModelAttribute Aluguel aluguel,
                               @RequestParam Long automovelId,
                               Principal principal) {
        aluguel.setStatus(StatusAluguel.PENDENTE);
        aluguel.setInicio(LocalDateTime.now());
        Cliente cliente = clienteService.obterClientePorCPF(principal.getName());
        aluguel.setCliente(cliente);
        aluguelService.criarAluguel(aluguel, automovelId);
        return "redirect:/alugueis?success=Aluguel+criado+com+sucesso";
    }

    @GetMapping("/{id}")
    public String detalhesAluguel(@PathVariable Long id, Model model) {
        Aluguel aluguel = aluguelService.obterAluguelPorId(id);
        model.addAttribute("aluguel", aluguel);
        return "aluguel/detalhes-aluguel";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditarAluguel(@PathVariable Long id, Model model) {
        Aluguel aluguel = aluguelService.obterAluguelPorId(id);
        model.addAttribute("aluguel", aluguel);
        return "aluguel/editar-aluguel";
    }

    @PostMapping("/{id}/atualizar")
    public String atualizarAluguel(@PathVariable Long id, @ModelAttribute Aluguel aluguel) {
        aluguelService.atualizarAluguel(id, aluguel);
        return "redirect:/alugueis?success=Aluguel+atualizado+com+sucesso";
    }

    @PostMapping("/{id}/encerrar")
    public String encerrarAluguel(@PathVariable Long id) {
        aluguelService.encerrarAluguel(id);
        return "redirect:/alugueis?success=Aluguel+encerrado+com+sucesso";
    }

    @PostMapping("/{id}/cancelar")
    public String cancelarAluguel(@PathVariable Long id) {
        aluguelService.cancelarAluguel(id);
        return "redirect:/alugueis?success=Aluguel+cancelado+com+sucesso";
    }

    @PostMapping("/{id}/deletar")
    public String deletarAluguel(@PathVariable Long id) {
        aluguelService.deletarAluguel(id);
        return "redirect:/alugueis?success=Aluguel+deletado+com+sucesso";
    }

    @GetMapping("/status/{status}")
    public String listarAlugueisPorStatus(@PathVariable StatusAluguel status, Model model) {
        List<Aluguel> alugueis = aluguelService.listarAlugueisPorStatus(status);
        model.addAttribute("alugueis", alugueis);
        return "aluguel/listar-alugueis";
    }
}
