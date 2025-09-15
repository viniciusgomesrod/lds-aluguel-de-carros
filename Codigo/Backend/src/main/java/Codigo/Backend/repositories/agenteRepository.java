package Codigo.Backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Codigo.Backend.models.Agente;

@Repository
public interface agenteRepository extends JpaRepository<Agente, Long> {
    Optional<Agente> findByCnpj(String cnpj);

    Void deleteByCnpj(String cnpj);
    
}