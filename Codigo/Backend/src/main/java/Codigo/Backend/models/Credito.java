package Codigo.Backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "creditos")
public class Credito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double valor;
    private Integer parcelas;
    private double valorParcelas;
    
    @Enumerated(EnumType.STRING)
    private StatusCredito statusCredito;
    
    
}