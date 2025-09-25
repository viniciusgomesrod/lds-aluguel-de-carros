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
@Table(name = "alugueis")
public class Aluguel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private StatusAluguel status;
    
    private LocalDateTime inicio;
    private LocalDateTime termino;
    private double valorTotal;
    
    
    public void encerrar() {
        this.status = StatusAluguel.ENCERRADO;
        this.termino = LocalDateTime.now();
    }
}