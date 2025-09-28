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

    @Autowired
    private Codigo.Backend.repositories.aluguelRepository aluguelRepository;

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
        try {
            aluguel.setStatus(StatusAluguel.PENDENTE);
            // Aqui você precisa obter o cliente logado pelo Principal
            Cliente cliente = clienteServices.obterClientePorEmail(principal.getName());
            aluguel.setCliente(cliente);

            aluguelService.criarAluguel(aluguel, automovelId);
            return "redirect:/clientes/automoveis?success=Reserva+criada+com+sucesso";
        } catch (Exception e) {
            return "redirect:/clientes/automoveis?error=Erro+ao+criar+reserva:+" + e.getMessage();
        }
    }

    // Cancelar aluguel
    @PostMapping("/alugueis/{id}/cancelar")
    public String cancelarAluguel(@PathVariable Long id) {
        aluguelService.cancelarAluguel(id);
        return "redirect:/clientes/automoveis?success=Reserva+cancelada+com+sucesso";
    }

    // Listar aluguéis do cliente
    @GetMapping("/alugueis")
    public String listarAlugueisCliente(Principal principal, Model model) {
        Cliente cliente = clienteServices.obterClientePorEmail(principal.getName());
        if (cliente != null) {
            List<Aluguel> alugueis = aluguelService.obterAlugueisPorUsuario(cliente.getId());
            model.addAttribute("alugueis", alugueis);
        }
        return "cliente/listar-alugueis";
    }

    // Visualizar detalhes de um aluguel específico
    @GetMapping("/alugueis/{id}")
    public String visualizarAluguel(@PathVariable Long id, Model model) {
        Aluguel aluguel = aluguelService.obterAluguelPorId(id);
        model.addAttribute("aluguel", aluguel);
        return "cliente/detalhes-aluguel";
    }

    // MÉTODOS PARA TELA EDITAR E EXCLUIR CONTA

    // Abrir formulário de edição
    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteServices.obterClientePorId(id);
        if (cliente == null) {
            return "redirect:/clientes/automoveis?error=Cliente+não+encontrado";
        }
        model.addAttribute("cliente", cliente);
        return "cliente/editar-conta"; // nova página HTML
    }

    // Atualizar cliente
    @PostMapping("/atualizar")
    public String atualizarCliente(@ModelAttribute Cliente cliente, Model model) {
        try {
            clienteServices.atualizarCliente(cliente);
            return "redirect:/clientes/automoveis?success=Dados+atualizados+com+sucesso";
        } catch (Exception e) {
            model.addAttribute("mensagem", "Erro ao atualizar: " + e.getMessage());
            return "cliente/editar-cliente";
        }
    }

    // Excluir cliente
    // Exemplo de como ficaria a lógica na sua ClienteController:

    @PostMapping("/excluir/{id}")
    public String excluirCliente(@PathVariable Long id) {
        try {
            // 1. Busque todos os aluguéis do cliente
            List<Aluguel> alugueis = aluguelRepository.findByCliente_Id(id);

            // 2. Verifique se há aluguéis "ativos" (Status APROVADO ou PENDENTE, por exemplo)
            boolean temAluguelAtivo = alugueis.stream()
                .anyMatch(aluguel -> "APROVADO".equals(aluguel.getStatus()) || "PENDENTE".equals(aluguel.getStatus()));

            if (temAluguelAtivo) {
                // REDIRECIONAMENTO COM PARÂMETRO 'error' (MENSAGEM URL ENCODED)
                return "redirect:/dashboardCliente?error=Não+é+possível+excluir+a+conta+com+aluguéis+ativos.";
            }
            
            // Se não houver aluguéis ativos
            clienteServices.deletarCliente(id);
            return "redirect:/login?success=Conta+excluída+com+sucesso";
            
        } catch (Exception e) {
            // Logar o erro e retornar mensagem genérica
            return "redirect:/dashboardCliente?error=Erro+ao+excluir+a+conta";
        }
    }

    

}
