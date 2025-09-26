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

   public Object loginPorEmail(String email) {
        var cliente = clienteRepository.findByEmail(email).orElse(null);
        if (cliente != null) return cliente;

        var agente = agenteRepository.findByEmail(email).orElse(null);
        if (agente != null) return agente;

        return null;
    }

    
}
