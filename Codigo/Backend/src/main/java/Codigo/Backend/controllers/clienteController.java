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
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class clienteController {

    @Autowired
    private clienteService clienteServices;

    @Autowired
    private aluguelService aluguelService;

    @Autowired
    private automovelService automovelService;

    // Formulário de cadastro
    @GetMapping("/cadastro")
    public String abrirFormulario(Model model) {
        Cliente novoCliente = new Cliente();
        List<Double> rendimentosIniciais = Arrays.asList(null, null, null);
        novoCliente.setRendimentosAuferidos(rendimentosIniciais);
        model.addAttribute("cliente", novoCliente);
        return "cadastro";
    }

    // Criar cliente
    @PostMapping("/criar")
    public String criarCliente(@ModelAttribute Cliente cliente, Model model) {
        try {
            clienteServices.criarCliente(cliente);
            model.addAttribute("mensagem", "Cliente cadastrado com sucesso!");
            Cliente novoCliente = new Cliente();
            List<Double> rendimentosIniciais = Arrays.asList(null, null, null);
            novoCliente.setRendimentosAuferidos(rendimentosIniciais);
            model.addAttribute("cliente", novoCliente);
        } catch (Exception e) {
            model.addAttribute("mensagem", "Erro ao cadastrar cliente: " + e.getMessage());
        }
        return "cadastro";
    }

    // Listar todos os automóveis disponíveis
    @GetMapping("/automoveis")
    public String listarAutomoveisDisponiveis(Model model) {
        List<Automovel> automoveis = automovelService.listarAutomoveisDisponiveis();
        model.addAttribute("automoveis", automoveis);
        return "cliente/listar-automoveis";
    }

    // Visualizar detalhes de um automóvel específico
    @GetMapping("/automoveis/{id}")
    public String visualizarAutomovel(@PathVariable Long id, Model model) {
        Automovel automovel = automovelService.obterAutomovelPorId(id);
        model.addAttribute("automovel", automovel);
        return "cliente/detalhes-automovel";
    }

    // Formulário para criar novo aluguel
    @GetMapping("/automoveis/{id}/alugar")
    public String formularioAluguel(@PathVariable Long id, Model model) {
        Automovel automovel = automovelService.obterAutomovelPorId(id);
        model.addAttribute("automovel", automovel);
        model.addAttribute("aluguel", new Aluguel());
        return "cliente/formulario-aluguel";
    }

    // Processar criação de aluguel
    @PostMapping("/alugueis/criar")
    public String criarAluguel(@ModelAttribute Aluguel aluguel,
                               @RequestParam Long automovelId,
                               Principal principal) {
        aluguel.setStatus(StatusAluguel.PENDENTE);
        aluguel.setInicio(LocalDateTime.now());
        // Aqui você precisa obter o cliente logado pelo Principal
        Cliente cliente = clienteServices.obterClientePorCPF(principal.getName());
        aluguel.setCliente(cliente);

        aluguelService.criarAluguel(aluguel, automovelId);
        return "redirect:/clientes/automoveis?success=Reserva+criada+com+sucesso";
    }

    // Cancelar aluguel
    @PostMapping("/alugueis/{id}/cancelar")
    public String cancelarAluguel(@PathVariable Long id) {
        aluguelService.cancelarAluguel(id);
        return "redirect:/clientes/automoveis?success=Reserva+cancelada+com+sucesso";
    }

    // Visualizar detalhes de um aluguel específico
    @GetMapping("/alugueis/{id}")
    public String visualizarAluguel(@PathVariable Long id, Model model) {
        Aluguel aluguel = aluguelService.obterAluguelPorId(id);
        model.addAttribute("aluguel", aluguel);
        return "cliente/detalhes-aluguel";
    }
}
