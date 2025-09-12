package Codigo.Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Codigo.Backend.models.Agente;

@Repository
public interface agenteRepository extends JpaRepository<Agente, Long> {
    
}