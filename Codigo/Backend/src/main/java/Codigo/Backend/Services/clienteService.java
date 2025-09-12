package Codigo.Backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Codigo.Backend.models.Cliente;
import Codigo.Backend.repositories.clienteRepository;

@Service
public class clienteService {
    
    @Autowired
    private clienteRepository clienteRepository;

    public void criarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente obterClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void atualizarCliente(Cliente cliente) {
        if (cliente.getId() == null || !clienteRepository.existsById(cliente.getId())) {
            throw new IllegalArgumentException("Cliente com ID inválido ou não existe.");
            
        }
        clienteRepository.save(cliente);
    }

    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente com ID inválido ou não existe.");
        }
        clienteRepository.deleteById(id);
    }
}