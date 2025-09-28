package Codigo.Backend.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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

    @Column(unique = true)
    private String CPF;

    @Column(unique = true)
    private String rg;

    private String profissao;

    public static final int MAX_EMPREGOS = 3;

   @ElementCollection(fetch = jakarta.persistence.FetchType.EAGER)
    @lombok.Builder.Default
    private List<Double> rendimentosAuferidos = new ArrayList<>();



}
