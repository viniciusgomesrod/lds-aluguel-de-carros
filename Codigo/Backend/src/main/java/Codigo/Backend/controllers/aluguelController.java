package Codigo.Backend.controllers;

import Codigo.Backend.Services.aluguelService;
import Codigo.Backend.models.Aluguel;
import Codigo.Backend.models.StatusAluguel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alugueis")
public class aluguelController {
    
    @Autowired
    private aluguelService aluguelService;

    @PostMapping("/criar")
    public ResponseEntity<Aluguel> criarAluguel(@RequestBody Aluguel aluguel) {
        try {
            aluguelService.criarAluguel(aluguel);
            return ResponseEntity.ok(aluguel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/obter/{id}")
    public ResponseEntity<Aluguel> obterAluguelPorId(@PathVariable Long id) {
        try {
            Aluguel aluguel = aluguelService.obterAluguelPorId(id);
            return ResponseEntity.ok(aluguel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Aluguel>> listarTodosAlugueis() {
        try {
            List<Aluguel> alugueis = aluguelService.listarTodosAlugueis();
            return ResponseEntity.ok(alugueis);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Aluguel>> listarAlugueisPorStatus(@PathVariable StatusAluguel status) {
        try {
            List<Aluguel> alugueis = aluguelService.listarAlugueisPorStatus(status);
            return ResponseEntity.ok(alugueis);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Aluguel>> obterAlugueisAtivos() {
        try {
            List<Aluguel> alugueisAtivos = aluguelService.obterAlugueisAtivos();
            return ResponseEntity.ok(alugueisAtivos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluguel> atualizarAluguel(@PathVariable Long id, @RequestBody Aluguel aluguel) {
        try {
            Aluguel aluguelAtualizado = aluguelService.atualizarAluguel(id, aluguel);
            return ResponseEntity.ok(aluguelAtualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/encerrar")
    public ResponseEntity<Aluguel> encerrarAluguel(@PathVariable Long id) {
        try {
            Aluguel aluguelEncerrado = aluguelService.encerrarAluguel(id);
            return ResponseEntity.ok(aluguelEncerrado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluguel(@PathVariable Long id) {
        try {
            aluguelService.deletarAluguel(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}