package Codigo.Backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import Codigo.Backend.models.Cliente;
import Codigo.Backend.repositories.clienteRepository;

@Service
public class clienteService {
    
    @Autowired
    private clienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //metodo para criar um cliente
    public void criarCliente(Cliente cliente) {
        String senhaCriptografada = passwordEncoder.encode(cliente.getSenha());
        cliente.setSenha(senhaCriptografada);
        clienteRepository.save(cliente);
    }

    //metodo para obter um cliente por id
    public Cliente obterClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    //metodo para obter um cliente por cpf
    public Cliente obterClientePorCPF(String cnpj) {
        return clienteRepository.findByCPF(cnpj);
    }

    //metodo para atualizar um cliente
    
    public void atualizarCliente(Cliente cliente) {
        if (cliente.getId() == null || !clienteRepository.existsById(cliente.getId())) {
            throw new IllegalArgumentException("Cliente com ID inválido ou não existe.");
        }
        
        Cliente clienteExistente = clienteRepository.findById(cliente.getId()).get();
        if (!cliente.getSenha().equals(clienteExistente.getSenha())) {
            cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        }

        clienteRepository.save(cliente);
    }


    //metodo para deletar um cliente
    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente com ID inválido ou não existe.");
        }
        clienteRepository.deleteById(id);
    }
}