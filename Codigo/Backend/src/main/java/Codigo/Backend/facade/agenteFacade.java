package Codigo.Backend.facade;

import org.springframework.stereotype.Component;
import Codigo.Backend.Services.agenteService;
import Codigo.Backend.models.Agente;

@Component  
public class agenteFacade {
    
    private final agenteService agenteService;
    
    public agenteFacade(agenteService agenteService) {
        this.agenteService = agenteService;
    }

    // Métodos que utilizam agenteService para operações relacionadas a Agente
    public void criarAgente(Agente agente) {
        agenteService.criarAgente(agente);
    }
    public Agente obterAgentePorId(Long id) {
        return agenteService.obterAgentePorId(id);
    }
    public void atualizarAgente(Agente agente) {
        agenteService.atualizarAgente(agente);
    }
    public void deletarAgente(Long id) {
        agenteService.deletarAgente(id);
    }
    
}
