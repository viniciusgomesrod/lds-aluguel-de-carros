package Codigo.Backend.Services;

import Codigo.Backend.models.Aluguel;
import Codigo.Backend.models.StatusAluguel;
import Codigo.Backend.repositories.aluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class aluguelService {
    
    @Autowired
    private aluguelRepository aluguelRepository;

    // Criar um novo aluguel
    public Aluguel criarAluguel(Aluguel aluguel) {
        aluguel.setStatus(StatusAluguel.APROVADO);
        aluguel.setInicio(LocalDateTime.now());
        return aluguelRepository.save(aluguel);
    }

    // Obter aluguel por ID
    public Aluguel obterAluguelPorId(Long id) {
        Optional<Aluguel> aluguel = aluguelRepository.findById(id);
        return aluguel.orElseThrow(() -> new RuntimeException("Aluguel não encontrado com ID: " + id));
    }

    // Listar todos os aluguéis
    public List<Aluguel> listarTodosAlugueis() {
        return aluguelRepository.findAll();
    }

    // Listar aluguéis por status
    public List<Aluguel> listarAlugueisPorStatus(StatusAluguel status) {
        return aluguelRepository.findByStatus(status);
    }

    // Atualizar aluguel
    public Aluguel atualizarAluguel(Long id, Aluguel aluguelAtualizado) {
        Aluguel aluguelExistente = obterAluguelPorId(id);
        
        aluguelExistente.setValorTotal(aluguelAtualizado.getValorTotal());
        aluguelExistente.setInicio(aluguelAtualizado.getInicio());
        aluguelExistente.setTermino(aluguelAtualizado.getTermino());
        aluguelExistente.setStatus(aluguelAtualizado.getStatus());
        
        return aluguelRepository.save(aluguelExistente);
    }

    // Encerrar aluguel
    public Aluguel encerrarAluguel(Long id) {
        Aluguel aluguel = obterAluguelPorId(id);
        aluguel.encerrar();
        return aluguelRepository.save(aluguel);
    }

    // Deletar aluguel
    public void deletarAluguel(Long id) {
        if (!aluguelRepository.existsById(id)) {
            throw new IllegalArgumentException("Aluguel com ID " + id + " não existe.");
        }
        aluguelRepository.deleteById(id);
    }

    // Obter aluguéis ativos
    public List<Aluguel> obterAlugueisAtivos() {
        return aluguelRepository.findByStatus(StatusAluguel.APROVADO);
    }
}