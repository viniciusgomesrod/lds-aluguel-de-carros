package Codigo.Backend.models;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Table(name = "agentes")
public class Agente extends Usuario {

    private String cnpj;
    private String razaoSocial;
    public Optional<Cliente> filter(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filter'");
    }
    
}
