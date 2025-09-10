package Codigo.Backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
public class Cliente extends Usuario {

    
    private String nome;
    private Endereco endereco;
    private String cpf;
    private String rg;
    private String profissao;
    public static final int MAX_EMPREGOS = 3;

}
