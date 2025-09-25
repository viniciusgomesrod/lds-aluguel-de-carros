package Codigo.Backend.repositories;

import Codigo.Backend.models.Aluguel;
import Codigo.Backend.models.StatusAluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface aluguelRepository extends JpaRepository<Aluguel, Long> {
    
    // Encontrar aluguéis por status
    List<Aluguel> findByStatus(StatusAluguel status);
    
    // Encontrar aluguéis ativos
    List<Aluguel> findByStatusOrderByInicioDesc(StatusAluguel status);
    
    // Encontrar aluguéis dentro de um período
    List<Aluguel> findByInicioBetween(LocalDateTime start, LocalDateTime end);
}