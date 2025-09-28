package Codigo.Backend.controllers;

import Codigo.Backend.Services.aluguelService;
import Codigo.Backend.Services.clienteService;
import Codigo.Backend.Services.agenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class dashboardController {
    
    private static final Logger logger = LoggerFactory.getLogger(dashboardController.class);
    
    @Autowired
    private clienteService clienteService;
    
    @Autowired
    private aluguelService aluguelService;
    
    @Autowired
    private agenteService agenteService;
    
    @Autowired
    private Codigo.Backend.Services.automovelService automovelService;
    
    @GetMapping("/dashboardCliente")
    public String dashboardCliente(Authentication authentication, Model model) {
        try {
            String email = authentication.getName();
            logger.info("Tentando buscar cliente com email: {}", email);
            
            // Buscar cliente pelo email em vez de CPF
            var cliente = clienteService.obterClientePorEmail(email);
            logger.info("Cliente encontrado: {}", cliente != null ? "Sim" : "Não");
            
            if (cliente != null) {
                var alugueis = aluguelService.obterAlugueisPorUsuario(cliente.getId());
                logger.info("Aluguéis encontrados: {}", alugueis.size());
                model.addAttribute("cliente", cliente);
                model.addAttribute("alugueis", alugueis);
            } else {
                logger.warn("Cliente não encontrado para email: {}", email);
                model.addAttribute("cliente", null);
                model.addAttribute("alugueis", java.util.Collections.emptyList());
            }
        } catch (Exception e) {
            logger.error("Erro ao carregar dados do usuário", e);
            model.addAttribute("cliente", null);
            model.addAttribute("alugueis", java.util.Collections.emptyList());
            model.addAttribute("error", "Erro ao carregar dados do usuário: " + e.getMessage());
        }
        
        return "dashboardCliente";
    }

    @GetMapping("/dashboardAgente")
    public String dashboardAgente(Authentication authentication, Model model) {
        try {
            String email = authentication.getName();
            logger.info("Tentando buscar agente com email: {}", email);
            
            // Buscar agente pelo email
            var agente = agenteService.obterAgentePorEmail(email);
            logger.info("Agente encontrado: {}", agente != null ? "Sim" : "Não");
            if (agente != null) {
                logger.info("Agente razaoSocial: {}", agente.getRazaoSocial());
            }
            
            if (agente != null) {
                // Buscar aluguéis pendentes para análise
                var alugueisPendentes = aluguelService.listarAlugueisPorStatus(Codigo.Backend.models.StatusAluguel.PENDENTE);
                var alugueisAprovados = aluguelService.listarAlugueisPorStatus(Codigo.Backend.models.StatusAluguel.APROVADO);
                var alugueisNegados = aluguelService.listarAlugueisPorStatus(Codigo.Backend.models.StatusAluguel.NEGADO);
                var todosAlugueis = aluguelService.listarTodosAlugueis();
                
                logger.info("Aluguéis pendentes: {}, aprovados: {}, negados: {}", 
                    alugueisPendentes.size(), alugueisAprovados.size(), alugueisNegados.size());
                
                model.addAttribute("agente", agente);
                model.addAttribute("alugueisPendentes", alugueisPendentes);
                model.addAttribute("alugueisAprovados", alugueisAprovados);
                model.addAttribute("alugueisNegados", alugueisNegados);
                model.addAttribute("totalAlugueis", todosAlugueis.size());
                model.addAttribute("totalPendentes", alugueisPendentes.size());
                model.addAttribute("totalAprovados", alugueisAprovados.size());
                model.addAttribute("totalNegados", alugueisNegados.size());
                
                // Carregar automóveis para o dashboard
                var automoveis = automovelService.listarTodosAutomoveis();
                model.addAttribute("automoveis", automoveis);
            } else {
                logger.warn("Agente não encontrado para email: {}", email);
                model.addAttribute("agente", null);
                model.addAttribute("alugueisPendentes", java.util.Collections.emptyList());
                model.addAttribute("alugueisAprovados", java.util.Collections.emptyList());
                model.addAttribute("alugueisNegados", java.util.Collections.emptyList());
                model.addAttribute("totalAlugueis", 0);
                model.addAttribute("totalPendentes", 0);
                model.addAttribute("totalAprovados", 0);
                model.addAttribute("totalNegados", 0);
            }
        } catch (Exception e) {
            logger.error("Erro ao carregar dados do agente", e);
            model.addAttribute("agente", null);
            model.addAttribute("alugueisPendentes", java.util.Collections.emptyList());
            model.addAttribute("alugueisAprovados", java.util.Collections.emptyList());
            model.addAttribute("alugueisNegados", java.util.Collections.emptyList());
            model.addAttribute("totalAlugueis", 0);
            model.addAttribute("totalPendentes", 0);
            model.addAttribute("totalAprovados", 0);
            model.addAttribute("totalNegados", 0);
            model.addAttribute("error", "Erro ao carregar dados do agente: " + e.getMessage());
        }
        
        return "dashboardAgente";
    }
}
