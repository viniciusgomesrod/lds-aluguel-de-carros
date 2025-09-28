package Codigo.Backend.repositories;

import Codigo.Backend.models.Aluguel;
import Codigo.Backend.models.Automovel;
import Codigo.Backend.models.StatusAluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface aluguelRepository extends JpaRepository<Aluguel, Long> {
    
    // Encontrar aluguéis por status único
    List<Aluguel> findByStatus(StatusAluguel status);

    // Encontrar aluguéis por múltiplos status (ativos, pendentes etc.)
    List<Aluguel> findByStatusIn(List<StatusAluguel> statusList);

    // Encontrar aluguéis por cliente
    List<Aluguel> findByCliente_Id(Long clienteId);
    
    // Encontrar aluguéis por automóvel
    List<Aluguel> findByAutomovelId(Long automovelId);
    
    // Encontrar aluguéis por nome do cliente
    List<Aluguel> findByCliente_Nome(String nome);

    // Encontrar aluguéis dentro de um período
    List<Aluguel> findByInicioBetween(LocalDateTime start, LocalDateTime end);

    // Encontrar aluguéis por automóvel e status
    boolean existsByAutomovelAndStatus(Automovel automovel, StatusAluguel status);

    // Encontrar aluguéis ativos ordenados pela data de início decrescente
    List<Aluguel> findByStatusOrderByInicioDesc(StatusAluguel status);

    // Encontrar aluguéis finalizados ou cancelados de um cliente
    List<Aluguel> findByCliente_IdAndStatusIn(Long clienteId, List<StatusAluguel> statusList);
}
