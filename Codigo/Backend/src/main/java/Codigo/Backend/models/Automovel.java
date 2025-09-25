package Codigo.Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "automoveis")
public class Automovel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String matricula;
    private Integer ano;
    private String marca;
    private String modelo;
    private String placa;
    private double valorDiaria;
    
    //como no diagrama é muitos carros pra 1 usuario, usar @manytoone
}