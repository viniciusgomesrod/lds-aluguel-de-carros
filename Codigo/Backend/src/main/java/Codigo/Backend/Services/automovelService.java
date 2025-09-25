package Codigo.Backend.Services;

import Codigo.Backend.models.Automovel;
import Codigo.Backend.repositories.automovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class automovelService {
    
    @Autowired
    private automovelRepository automovelRepository;

    // Criar um novo automóvel
    public Automovel criarAutomovel(Automovel automovel) {
        // Verificar se já existe automóvel com a mesma placa
        if (automovelRepository.findByPlaca(automovel.getPlaca()) != null) {
            throw new IllegalArgumentException("Já existe um automóvel com esta placa: " + automovel.getPlaca());
        }
        
        // Verificar se já existe automóvel com a mesma matrícula
        if (automovelRepository.findByMatricula(automovel.getMatricula()) != null) {
            throw new IllegalArgumentException("Já existe um automóvel com esta matrícula: " + automovel.getMatricula());
        }
        
        return automovelRepository.save(automovel);
    }

    // Obter automóvel por ID
    public Automovel obterAutomovelPorId(Long id) {
        Optional<Automovel> automovel = automovelRepository.findById(id);
        return automovel.orElseThrow(() -> new RuntimeException("Automóvel não encontrado com ID: " + id));
    }

    // Obter automóvel por placa
    public Automovel obterAutomovelPorPlaca(String placa) {
        Automovel automovel = automovelRepository.findByPlaca(placa);
        if (automovel == null) {
            throw new RuntimeException("Automóvel não encontrado com a placa: " + placa);
        }
        return automovel;
    }

    // Listar todos os automóveis
    public List<Automovel> listarTodosAutomoveis() {
        return automovelRepository.findAll();
    }

    // Listar automóveis por marca
    public List<Automovel> listarAutomoveisPorMarca(String marca) {
        return automovelRepository.findByMarca(marca);
    }

    // Listar automóveis por modelo
    public List<Automovel> listarAutomoveisPorModelo(String modelo) {
        return automovelRepository.findByModelo(modelo);
    }

    // Listar automóveis por ano
    public List<Automovel> listarAutomoveisPorAno(Integer ano) {
        return automovelRepository.findByAno(ano);
    }

    // Atualizar automóvel
    public Automovel atualizarAutomovel(Long id, Automovel automovelAtualizado) {
        Automovel automovelExistente = obterAutomovelPorId(id);
        
        // Verificar se a placa foi alterada e se já existe
        if (!automovelExistente.getPlaca().equals(automovelAtualizado.getPlaca())) {
            Automovel automovelComPlaca = automovelRepository.findByPlaca(automovelAtualizado.getPlaca());
            if (automovelComPlaca != null && !automovelComPlaca.getId().equals(id)) {
                throw new IllegalArgumentException("Já existe outro automóvel com esta placa: " + automovelAtualizado.getPlaca());
            }
        }
        
        // Verificar se a matrícula foi alterada e se já existe
        if (!automovelExistente.getMatricula().equals(automovelAtualizado.getMatricula())) {
            Automovel automovelComMatricula = automovelRepository.findByMatricula(automovelAtualizado.getMatricula());
            if (automovelComMatricula != null && !automovelComMatricula.getId().equals(id)) {
                throw new IllegalArgumentException("Já existe outro automóvel com esta matrícula: " + automovelAtualizado.getMatricula());
            }
        }
        
        automovelExistente.setMatricula(automovelAtualizado.getMatricula());
        automovelExistente.setAno(automovelAtualizado.getAno());
        automovelExistente.setMarca(automovelAtualizado.getMarca());
        automovelExistente.setModelo(automovelAtualizado.getModelo());
        automovelExistente.setPlaca(automovelAtualizado.getPlaca());
        automovelExistente.setValorDiaria(automovelAtualizado.getValorDiaria());
        
        return automovelRepository.save(automovelExistente);
    }

    // Deletar automóvel
    public void deletarAutomovel(Long id) {
        if (!automovelRepository.existsById(id)) {
            throw new IllegalArgumentException("Automóvel com ID " + id + " não existe.");
        }
        automovelRepository.deleteById(id);
    }

    // Verificar se automóvel existe por ID
    public boolean automovelExiste(Long id) {
        return automovelRepository.existsById(id);
    }
}