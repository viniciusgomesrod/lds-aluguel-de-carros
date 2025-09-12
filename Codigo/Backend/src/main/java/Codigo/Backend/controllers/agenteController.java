package Codigo.Backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Codigo.Backend.facade.agenteFacade;
import Codigo.Backend.models.Agente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/agentes")
public class agenteController {

    private final agenteFacade agenteFacade;
    
    public agenteController(agenteFacade agenteFacade) {
        this.agenteFacade = agenteFacade;
    }
    // Endpoints que utilizam agenteFacade para operações relacionadas a Agente
    @PostMapping("/criar")
    public void criarAgente(Agente agente) {
        agenteFacade.criarAgente(agente);
    }           
    
    @GetMapping("/obter/{id}")
    public Agente obterAgentePorId(Long id) {
        return agenteFacade.obterAgentePorId(id);   
    }

    @PutMapping("/atualizar")
    public void atualizarAgente(Agente agente) {
        agenteFacade.atualizarAgente(agente);
    }
    
    @DeleteMapping("/deletar/{id}")
    public void deletarAgente(Long id) {   
        agenteFacade.deletarAgente(id);
    }

}
