package Codigo.Backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Codigo.Backend.Services.clienteService;
import Codigo.Backend.models.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/clientes")
public class clienteController {
    
    @Autowired
    private clienteService clienteServices;

    @PostMapping("/criar")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        try {
            clienteServices.criarCliente(cliente);
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/obter/{id}")
    public ResponseEntity<Cliente> obterClienteId(Long id) {
        try {
            clienteServices.obterClientePorId(id);
            return ResponseEntity.status(201).body(clienteServices.obterClientePorId(id));
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível encontrar o cliente com o ID fornecido.");
        }
    }

    @PutMapping("/atualizar")
    public String atualizarCliente(@RequestParam String CPF, @RequestBody Cliente cliente) {
        try {
            clienteServices.atualizarCliente(cliente);
            return "Cliente atualizado com sucesso.";
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível atualizar o cliente com o CPF fornecido.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteServices.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
    
    
    
}
