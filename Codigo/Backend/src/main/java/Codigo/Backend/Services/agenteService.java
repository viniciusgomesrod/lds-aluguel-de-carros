package Codigo.Backend.Services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import Codigo.Backend.models.Agente;
import Codigo.Backend.repositories.agenteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class agenteService {

    @Autowired
    private agenteRepository agenteRepository;

    //metodo para criar um agente
    public void criarAgente(Agente agente) {
        agenteRepository.save(agente);
    }
    
    //metodo para obter um agente por id
    public Agente obterAgentePorId(Long id) {
        return agenteRepository.findById(id).orElseThrow
        (()-> new EntityNotFoundException("Agente com ID inválido ou não existe."));
    }

    //metodo para obter um agente por cnpj
    public Agente obterAgentePorCnpj(String cnpj) {
        return agenteRepository.findByCnpj(cnpj).orElseThrow(
        ()-> new EntityNotFoundException("Agente com CNPJ inválido ou não existe."));
    }

    //metodo para atualizar um agente
    public Agente atualizarAgente(String cnpj, Agente agente) {
        Agente agenteAtual = obterAgentePorCnpj(cnpj);
        Agente Atualizado = agente.builder()
            .id(agenteAtual.getId())
            .cnpj(agente.getCnpj())
            .telefone(agente.getTelefone())
            .email(agente.getEmail())
            .build();
        agenteRepository.save(Atualizado);
        return Atualizado;
    }
    
    //metodo para deletar um agente
    public void deletarAgente(String cnpj) {
        if (!agenteRepository.findByCnpj(cnpj).isPresent()) {
            throw new IllegalArgumentException("Agente com ID inválido ou não existe.");
        }
        agenteRepository.deleteByCnpj(cnpj);
    }
    
}
