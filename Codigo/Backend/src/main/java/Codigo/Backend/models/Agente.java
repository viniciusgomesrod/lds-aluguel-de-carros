package Codigo.Backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agentes")
public class Agente extends Usuario {

    private Long id;
    private String cnpj;
    private String razaoSocial;
    
}
