package Codigo.Backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Codigo.Backend.Services.agenteService;
import Codigo.Backend.models.Agente;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/agentes")
@RequiredArgsConstructor
public class agenteController {

    private final agenteService agenteService;

    @PostMapping("/criar")
    public ResponseEntity<Agente> criarAgente( @RequestBody Agente agente) {
        try {
            agenteService.criarAgente(agente);
            return ResponseEntity.ok(agente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
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
