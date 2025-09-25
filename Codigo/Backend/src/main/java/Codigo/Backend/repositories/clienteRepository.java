package Codigo.Backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Codigo.Backend.models.Cliente;


@Repository
public interface clienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByCPF(String cpf);
    Optional<Cliente> findByEmail(String email);
}