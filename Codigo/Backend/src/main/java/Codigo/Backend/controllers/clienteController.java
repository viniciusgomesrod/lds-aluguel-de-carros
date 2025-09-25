package Codigo.Backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Codigo.Backend.Services.clienteService;
import Codigo.Backend.models.Cliente;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequestMapping("/clientes")
public class clienteController {
    
    @Autowired
    private clienteService clienteServices;

    @GetMapping("/cadastro")
    public String abrirFormulario(Model model){
        Cliente novoCliente = new Cliente();
        List<Double> rendimentosIniciais = Arrays.asList(null, null, null);
        novoCliente.setRendimentosAuferidos(rendimentosIniciais);
        model.addAttribute("cliente", novoCliente);
        return "cadastro";
    }

    @PostMapping("/criar")
    public String criarCliente(@ModelAttribute Cliente cliente, Model model) {
        try {
            clienteServices.criarCliente(cliente);
            model.addAttribute("mensagem", "Cliente cadastrado com sucesso!");
            Cliente novoCliente = new Cliente();
            List<Double> rendimentosIniciais = Arrays.asList(null, null, null);
            novoCliente.setRendimentosAuferidos(rendimentosIniciais);
            model.addAttribute("cliente", novoCliente);
           
        } catch (Exception e) {
             model.addAttribute("mensagem", "Erro ao cadastrar cliente: " + e.getMessage());
        }
        return "cadastro";
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
