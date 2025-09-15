package Codigo.Backend.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "clientes")
public class Cliente extends Usuario {

    private String nome;

    @Embedded
    private Endereco endereco;

    private String CPF;

    private String rg;

    private String profissao;

    public static final int MAX_EMPREGOS = 3;

    private double redimentosAuferidos;

}
