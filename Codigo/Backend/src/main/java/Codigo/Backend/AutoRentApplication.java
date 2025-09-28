package Codigo.Backend;

import Codigo.Backend.Services.agenteService;
import Codigo.Backend.Services.clienteService;
import Codigo.Backend.Services.automovelService;
import Codigo.Backend.models.Agente;
import Codigo.Backend.models.Cliente;
import Codigo.Backend.models.Automovel;
import Codigo.Backend.models.Endereco;
import Codigo.Backend.repositories.agenteRepository;
import Codigo.Backend.repositories.clienteRepository;
import Codigo.Backend.repositories.automovelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class AutoRentApplication implements CommandLineRunner {

    @Autowired
    private agenteRepository agenteRepository;

    @Autowired
    private clienteRepository clienteRepository;

    @Autowired
    private automovelRepository automovelRepository;

    @Autowired
    private agenteService agenteService;

    @Autowired
    private clienteService clienteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(AutoRentApplication.class, args);
    }

    //cria dados de teste para login
    @Override
    public void run(String... args) throws Exception {
        criarAgenteTeste();
        criarClienteTeste();
        criarAutomoveisTeste();
    }

    private void criarAgenteTeste() {
        String email = "teste@empresa.com";
        if(agenteRepository.findByEmail(email).isEmpty()){
             Agente agente = Agente.builder()
                .email(email)
                .senha(passwordEncoder.encode("123456"))
                .telefone("31999999999")
                .cnpj("12345678000199")
                .razaoSocial("Empresa Teste")
                .build();

        agenteService.criarAgente(agente);
        System.out.println("Agente criado com sucesso!");
        }else{
            System.out.println("Agente já foi criado");
        }
    }

    private void criarClienteTeste() {
        String email = "cliente@teste.com";
        if(clienteRepository.findByEmail(email).isEmpty()){
            Endereco endereco = new Endereco();
            endereco.setLogradouro("Rua das Flores");
            endereco.setNumero("123");
            endereco.setBairro("Centro");
            endereco.setCidade("Belo Horizonte");
            endereco.setEstado("MG");
            endereco.setCep("30000-000");

            Cliente cliente = Cliente.builder()
                .email(email)
                .senha(passwordEncoder.encode("123456"))
                .telefone("31988888888")
                .nome("João Silva")
                .CPF("12345678901")
                .rg("1234567")
                .profissao("Desenvolvedor")
                .endereco(endereco)
                .rendimentosAuferidos(Arrays.asList(5000.0, 5500.0, 6000.0))
                .build();

            clienteService.criarCliente(cliente);
            System.out.println("Cliente criado com sucesso!");
        }else{
            System.out.println("Cliente já foi criado");
        }
    }

    private void criarAutomoveisTeste() {
        if(automovelRepository.count() == 0){
            // Automóvel 1
            Automovel auto1 = new Automovel();
            auto1.setMatricula("AUTO001");
            auto1.setAno(2022);
            auto1.setMarca("Toyota");
            auto1.setModelo("Corolla");
            auto1.setPlaca("ABC-1234");
            auto1.setValorDiaria(150.0);
            auto1.setDisponivel(true);
            automovelRepository.save(auto1);

            // Automóvel 2
            Automovel auto2 = new Automovel();
            auto2.setMatricula("AUTO002");
            auto2.setAno(2023);
            auto2.setMarca("Honda");
            auto2.setModelo("Civic");
            auto2.setPlaca("DEF-5678");
            auto2.setValorDiaria(180.0);
            auto2.setDisponivel(true);
            automovelRepository.save(auto2);

            // Automóvel 3
            Automovel auto3 = new Automovel();
            auto3.setMatricula("AUTO003");
            auto3.setAno(2021);
            auto3.setMarca("Volkswagen");
            auto3.setModelo("Golf");
            auto3.setPlaca("GHI-9012");
            auto3.setValorDiaria(120.0);
            auto3.setDisponivel(true);
            automovelRepository.save(auto3);

            System.out.println("Automóveis de teste criados com sucesso!");
        }else{
            System.out.println("Automóveis já foram criados");
        }
    }
}
