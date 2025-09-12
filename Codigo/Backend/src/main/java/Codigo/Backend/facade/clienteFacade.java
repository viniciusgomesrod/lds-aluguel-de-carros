package Codigo.Backend.facade;

import org.springframework.stereotype.Component;
import Codigo.Backend.Services.clienteService;
import Codigo.Backend.models.Cliente;

@Component
public class clienteFacade {
    
    private final clienteService clienteService;
    
    public clienteFacade(clienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Métodos que utilizam clienteService para operações relacionadas a Cliente
    public void criarCliente(Cliente cliente) {
        clienteService.criarCliente(cliente);
    }
    public Cliente obterClientePorId(Long id) {
        return clienteService.obterClientePorId(id);
    }
    public void atualizarCliente(Cliente cliente) {
        clienteService.atualizarCliente(cliente);
    }
    public void deletarCliente(Long id) {
        clienteService.deletarCliente(id);
    }

}