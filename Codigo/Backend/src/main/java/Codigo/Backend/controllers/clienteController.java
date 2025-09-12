package Codigo.Backend.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Codigo.Backend.facade.clienteFacade;
import Codigo.Backend.models.Cliente;

@RestController
@RequestMapping("/clientes")
public class clienteController {
    
    private final clienteFacade clienteFacade;
    
    public clienteController(clienteFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    // Endpoints que utilizam clienteFacade para operações relacionadas a Cliente
    @PostMapping("/criar")
    public void criarCliente(Cliente cliente) {
        clienteFacade.criarCliente(cliente);
    }

    @GetMapping("/obter/{id}")
    public Cliente obterClientePorId(Long id) {
        return clienteFacade.obterClientePorId(id);
    }

    @PutMapping("/atualizar")
    public void atualizarCliente(Cliente cliente) {
        clienteFacade.atualizarCliente(cliente);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarCliente(Long id) {
        clienteFacade.deletarCliente(id);
    }
}
