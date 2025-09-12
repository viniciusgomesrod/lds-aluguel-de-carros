package Codigo.Backend.Services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import Codigo.Backend.models.Agente;
import Codigo.Backend.repositories.agenteRepository;

@Service
public class agenteService {

    @Autowired
    private agenteRepository agenteRepository;

    public void criarAgente(Agente agente) {
        agenteRepository.save(agente);
    }

    public Agente obterAgentePorId(Long id) {
        return agenteRepository.findById(id).orElse(null);
    }

    public void atualizarAgente(Agente agente) {
        if (agente.getId() == null || !agenteRepository.existsById(agente.getId())) {
            throw new IllegalArgumentException("Agente com ID inválido ou não existe.");
        }
        agenteRepository.save(agente);
    }
    
    public void deletarAgente(Long id) {
        if (!agenteRepository.existsById(id)) {
            throw new IllegalArgumentException("Agente com ID inválido ou não existe.");
        }
        agenteRepository.deleteById(id);
    }
    
}
