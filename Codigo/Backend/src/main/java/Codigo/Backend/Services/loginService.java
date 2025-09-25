package Codigo.Backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Codigo.Backend.repositories.agenteRepository;
import Codigo.Backend.repositories.clienteRepository;

@Service
public class loginService {
    @Autowired
    private agenteRepository agenteRepository;

    @Autowired
    private clienteRepository clienteRepository;

    public Object login(String email, String senha){
        var cliente = clienteRepository.findByEmail(email)
        .filter(c -> c.getSenha().equals(senha)).orElse(null);

        if(cliente != null){
            return cliente;
        }

        var agente = agenteRepository.findByEmail(email)
        .filter(a -> a.getSenha().equals(senha)).orElse(null);

        if(agente != null){
            return agente;
        }
        
        return null;
    }
    
}
