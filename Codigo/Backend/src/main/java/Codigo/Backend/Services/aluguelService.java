package Codigo.Backend.Services;

import Codigo.Backend.models.Aluguel;
import Codigo.Backend.models.Automovel;
import Codigo.Backend.models.StatusAluguel;
import Codigo.Backend.repositories.aluguelRepository;
import Codigo.Backend.repositories.automovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class aluguelService {

    @Autowired
    private aluguelRepository aluguelRepository;

    @Autowired
    private automovelRepository automovelRepository;

    // Listar todos os aluguéis
    public List<Aluguel> listarTodosAlugueis() {
        return aluguelRepository.findAll();
    }

    // Listar aluguéis por usuário (assumindo que Aluguel tem atributo Cliente/Usuario)
    public List<Aluguel> obterAlugueisPorUsuario(Long clienteId) {
        return aluguelRepository.findByCliente_Id(clienteId);
    }

    // Listar aluguéis por status
    public List<Aluguel> listarAlugueisPorStatus(StatusAluguel status) {
        return aluguelRepository.findByStatus(status);
    }

    // Obter aluguéis ativos (pendente ou aprovado)
    public List<Aluguel> obterAlugueisAtivos() {
        return aluguelRepository.findByStatusIn(List.of(StatusAluguel.PENDENTE, StatusAluguel.APROVADO));
    }

    // Obter aluguel por ID
    public Aluguel obterAluguelPorId(Long id) {
        return aluguelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluguel não encontrado"));
    }

    // Criar aluguel associando a um automóvel
    public Aluguel criarAluguel(Aluguel aluguel, Long automovelId) {
        Automovel automovel = automovelRepository.findById(automovelId)
                .orElseThrow(() -> new RuntimeException("Automóvel não encontrado"));

        if (!automovel.isDisponivel()) {
            throw new RuntimeException("Automóvel não está disponível para aluguel");
        }

        aluguel.setAutomovel(automovel);
        aluguel.setStatus(StatusAluguel.PENDENTE);

        // Marcar automóvel como indisponível
        automovel.setDisponivel(false);
        automovelRepository.save(automovel);

        return aluguelRepository.save(aluguel);
    }

    // Atualizar aluguel
    public Aluguel atualizarAluguel(Long id, Aluguel aluguelAtualizado) {
        Aluguel aluguel = obterAluguelPorId(id);

        aluguel.setInicio(aluguelAtualizado.getInicio());
        aluguel.setStatus(aluguelAtualizado.getStatus());
        aluguel.setTermino(aluguelAtualizado.getTermino());
        aluguel.setValorTotal(aluguelAtualizado.getValorTotal());

        return aluguelRepository.save(aluguel);
    }

    // Encerrar aluguel
    public void encerrarAluguel(Long id) {
        Aluguel aluguel = obterAluguelPorId(id);
        aluguel.setStatus(StatusAluguel.ENCERRADO);

        // Liberar automóvel
        Automovel automovel = aluguel.getAutomovel();
        if (automovel != null) {
            automovel.setDisponivel(true);
            automovelRepository.save(automovel);
        }

        aluguelRepository.save(aluguel);
    }

    // Cancelar aluguel
    public void cancelarAluguel(Long id) {
        Aluguel aluguel = obterAluguelPorId(id);
        aluguel.setStatus(StatusAluguel.NEGADO);

        Automovel automovel = aluguel.getAutomovel();
        if (automovel != null) {
            automovel.setDisponivel(true);
            automovelRepository.save(automovel);
        }

        aluguelRepository.save(aluguel);
    }

    // Deletar aluguel
    public void deletarAluguel(Long id) {
        Aluguel aluguel = obterAluguelPorId(id);

        Automovel automovel = aluguel.getAutomovel();
        if (automovel != null && (aluguel.getStatus() == StatusAluguel.PENDENTE || aluguel.getStatus() == StatusAluguel.APROVADO)) {
            automovel.setDisponivel(true);
            automovelRepository.save(automovel);
        }

        aluguelRepository.delete(aluguel);
    }

    // Aprovar aluguel
    public void aprovarAluguel(Long id) {
        Aluguel aluguel = obterAluguelPorId(id);
        aluguel.setStatus(StatusAluguel.APROVADO);
        aluguelRepository.save(aluguel);
    }

    // Rejeitar aluguel
    public void rejeitarAluguel(Long id, String motivo) {
        Aluguel aluguel = obterAluguelPorId(id);
        aluguel.setStatus(StatusAluguel.NEGADO);
        
        // Liberar automóvel se estava reservado
        Automovel automovel = aluguel.getAutomovel();
        if (automovel != null) {
            automovel.setDisponivel(true);
            automovelRepository.save(automovel);
        }
        
        aluguelRepository.save(aluguel);
    }
}
