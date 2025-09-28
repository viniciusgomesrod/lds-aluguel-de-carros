package Codigo.Backend.repositories;

import Codigo.Backend.models.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface automovelRepository extends JpaRepository<Automovel, Long> {
    
    // Encontrar automóvel por placa
    Automovel findByPlaca(String placa);
    
    // Encontrar automóvel por matrícula
    Automovel findByMatricula(String matricula);
    
    // Encontrar automóveis por marca
    List<Automovel> findByMarca(String marca);
    
    // Encontrar automóveis por modelo
    List<Automovel> findByModelo(String modelo);
    
    // Encontrar automóveis por ano
    List<Automovel> findByAno(Integer ano);
    
    // Encontrar automóveis por marca e modelo
    List<Automovel> findByMarcaAndModelo(String marca, String modelo);

     // Método para buscar apenas automóveis disponíveis
    List<Automovel> findByDisponivelTrue();
    
}