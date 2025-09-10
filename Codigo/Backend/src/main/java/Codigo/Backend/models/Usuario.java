package Codigo.Backend.models;

import lombok.Data;

@Data
public abstract class Usuario {
    private String email;
    private String senha;
    private String telefone;

    

}
