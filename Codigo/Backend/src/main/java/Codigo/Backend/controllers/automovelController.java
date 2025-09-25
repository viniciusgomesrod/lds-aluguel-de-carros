package Codigo.Backend.controllers;

import Codigo.Backend.Services.automovelService;
import Codigo.Backend.models.Automovel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/automoveis")
public class automovelController {
    
    @Autowired
    private automovelService automovelService;

    @PostMapping("/criar")
    public ResponseEntity<?> criarAutomovel(@RequestBody Automovel automovel) {
        try {
            Automovel novoAutomovel = automovelService.criarAutomovel(automovel);
            return ResponseEntity.ok(novoAutomovel);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Automovel> obterAutomovelPorId(@PathVariable Long id) {
        try {
            Automovel automovel = automovelService.obterAutomovelPorId(id);
            return ResponseEntity.ok(automovel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<Automovel> obterAutomovelPorPlaca(@PathVariable String placa) {
        try {
            Automovel automovel = automovelService.obterAutomovelPorPlaca(placa);
            return ResponseEntity.ok(automovel);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Automovel>> listarTodosAutomoveis() {
        try {
            List<Automovel> automoveis = automovelService.listarTodosAutomoveis();
            return ResponseEntity.ok(automoveis);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Automovel>> listarAutomoveisPorMarca(@PathVariable String marca) {
        try {
            List<Automovel> automoveis = automovelService.listarAutomoveisPorMarca(marca);
            return ResponseEntity.ok(automoveis);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<List<Automovel>> listarAutomoveisPorModelo(@PathVariable String modelo) {
        try {
            List<Automovel> automoveis = automovelService.listarAutomoveisPorModelo(modelo);
            return ResponseEntity.ok(automoveis);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<Automovel>> listarAutomoveisPorAno(@PathVariable Integer ano) {
        try {
            List<Automovel> automoveis = automovelService.listarAutomoveisPorAno(ano);
            return ResponseEntity.ok(automoveis);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAutomovel(@PathVariable Long id, @RequestBody Automovel automovel) {
        try {
            Automovel automovelAtualizado = automovelService.atualizarAutomovel(id, automovel);
            return ResponseEntity.ok(automovelAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutomovel(@PathVariable Long id) {
        try {
            automovelService.deletarAutomovel(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}