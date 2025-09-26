package Codigo.Backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Codigo.Backend.Services.loginService;
import Codigo.Backend.models.Cliente;
import Codigo.Backend.models.Agente;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private loginService loginService;

   @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Object usuario = loginService.loginPorEmail(email); // busca só pelo email

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        if (usuario instanceof Cliente cliente) {
            return new ClienteUserDetails(cliente);
        } else if (usuario instanceof Agente agente) {
            return new AgenteUserDetails(agente);
        }

        throw new UsernameNotFoundException("Usuário não suportado");
    }

}
