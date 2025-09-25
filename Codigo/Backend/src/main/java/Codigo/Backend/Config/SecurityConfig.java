package Codigo.Backend.Config; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(authorize -> authorize
                // Permite acesso irrestrito às URLs de cadastro e a todos os recursos estáticos.
                .requestMatchers("/clientes/cadastro", "/clientes/criar", "/css/**", "/js/**", "/images/**").permitAll()
                // Todas as outras requisições devem ser autenticadas.
                .anyRequest().authenticated()
            )
            // Habilita a autenticação via formulário e configura a página de login
            .formLogin(formLogin -> formLogin
                .loginPage("/login") // Especifica a URL da página de login.
                .permitAll() // Garante que a página de login e o POST para ela estejam acessíveis a todos.
            )
            // Permite o logout
            .logout(logout -> logout
                .permitAll()
            );

        return http.build();
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Mapeia a URL / para a view de login.
        registry.addViewController("/").setViewName("login");
        // Mapeia a URL /login para a view de login.
        registry.addViewController("/login").setViewName("login");
    }
}
