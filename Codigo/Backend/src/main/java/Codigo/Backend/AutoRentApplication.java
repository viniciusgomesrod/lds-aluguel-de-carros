package Codigo.Backend;

import Codigo.Backend.Services.agenteService;
import Codigo.Backend.models.Agente;
import Codigo.Backend.repositories.agenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AutoRentApplication implements CommandLineRunner {

    @Autowired
    private  agenteRepository agenteRepository;

    @Autowired
    private agenteService agenteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(AutoRentApplication.class, args);
    }

    //cria agente para testar no login
    @Override
    public void run(String... args) throws Exception {
        String email = "teste@empresa.com";

        if(agenteRepository.findByEmail(email) == null){
             Agente agente = Agente.builder()
                .email(email)
                .senha(passwordEncoder.encode("123456"))
                .telefone("31999999999")
                .cnpj("12345678000199")
                .razaoSocial("Empresa Teste")
                .build();

        agenteService.criarAgente(agente);
        System.out.println("Agente criado com sucesso pelo terminal!");
        }else{
            System.out.println("Agente ja foi criado");
        }
    }
}
