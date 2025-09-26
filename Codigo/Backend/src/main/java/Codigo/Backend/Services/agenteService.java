package Codigo.Backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import Codigo.Backend.models.Agente;
import Codigo.Backend.repositories.agenteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class agenteService {

    @Autowired
    private agenteRepository agenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // método para criar um agente
    public void criarAgente(Agente agente) {
        // Criptografa a senha antes de salvar
        String senhaCriptografada = passwordEncoder.encode(agente.getSenha());
        agente.setSenha(senhaCriptografada);

        agenteRepository.save(agente);
    }

    // método para obter um agente por id
    public Agente obterAgentePorId(Long id) {
        return agenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agente com ID inválido ou não existe."));
    }

    // método para obter um agente por CNPJ
    public Agente obterAgentePorCnpj(String cnpj) {
        return agenteRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new EntityNotFoundException("Agente com CNPJ inválido ou não existe."));
    }

    // método para atualizar um agente
    public Agente atualizarAgente(String cnpj, Agente agente) {
        Agente agenteAtual = obterAgentePorCnpj(cnpj);

        // Recriptografa a senha se for diferente da existente
        if (!agente.getSenha().equals(agenteAtual.getSenha())) {
            agente.setSenha(passwordEncoder.encode(agente.getSenha()));
        }

        Agente atualizado = agente.builder()
                .id(agenteAtual.getId())
                .cnpj(agente.getCnpj())
                .telefone(agente.getTelefone())
                .email(agente.getEmail())
                .senha(agente.getSenha())
                .build();

        agenteRepository.save(atualizado);
        return atualizado;
    }

    // método para deletar um agente
    public void deletarAgente(String cnpj) {
        if (!agenteRepository.findByCnpj(cnpj).isPresent()) {
            throw new IllegalArgumentException("Agente com CNPJ inválido ou não existe.");
        }
        agenteRepository.deleteByCnpj(cnpj);
    }
}
