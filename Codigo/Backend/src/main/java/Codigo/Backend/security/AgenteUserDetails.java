package Codigo.Backend.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import Codigo.Backend.models.Agente;

public class AgenteUserDetails implements UserDetails {

    private Agente agente;

    public AgenteUserDetails(Agente agente) {
        this.agente = agente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_AGENTE"));
    }

    @Override
    public String getPassword() {
        return agente.getSenha();
    }

    @Override
    public String getUsername() {
        return agente.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    public Agente getAgente() { return agente; }
}
