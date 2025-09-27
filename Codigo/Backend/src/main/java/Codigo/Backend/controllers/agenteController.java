package Codigo.Backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Codigo.Backend.Services.agenteService;
import Codigo.Backend.models.Agente;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/agentes")
@RequiredArgsConstructor
public class agenteController {

    @Autowired
    private final agenteService agenteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/criar")
    public String criarAgente(@ModelAttribute Agente agente, Model model) {
        try {
            agente.setSenha(passwordEncoder.encode(agente.getSenha()));
            agenteService.criarAgente(agente);
            model.addAttribute("mensagem", "Agente cadastrado com sucesso!");
            return "paginaDeSucesso"; //alterar
        } catch (Exception e) {
            model.addAttribute("erro", "Falha ao cadastrar agente");
            return "formularioAgente"; //alterar
        }
    }
    
    
    @GetMapping("/obter/{id}")
    public ResponseEntity<?> obterAgentePorId(Long id) {
        try {
            agenteService.obterAgentePorId(id);
            return ResponseEntity.status(201).body(agenteService.obterAgentePorId(id));
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível encontrar o agente com o ID fornecido.");
        }  
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Agente> atualizarAgente(@RequestParam String cnpj, @RequestBody Agente agente) {
        try {
            agenteService.atualizarAgente(cnpj, agente);
            return ResponseEntity.ok(agente);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível atualizar o agente com o CNPJ fornecido.");
        }
        
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarAgente(String cnpj) {   
        agenteService.deletarAgente(cnpj);
        return ResponseEntity.noContent().build();
    }

}
