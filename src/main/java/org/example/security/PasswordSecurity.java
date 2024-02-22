package org.example.security;

import org.example.model.Cliente;
import org.example.repository.ClienteRepository;
import org.example.service.ClienteService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Scanner;

public class PasswordSecurity {
    private ClienteRepository clienteRepository;


    public String encriptarSenha(String senha) {

        //senha que sera encriptada
        var hashSenha = BCrypt.hashpw(senha, BCrypt.gensalt());
        return hashSenha;
    }

//    public String checkSenha(String senha, Scanner scanner) {
//        //forma de verificar a senha
//
//        String hashSenha = clienteRepository.consultarClientePorSenha(senha)
//        if (BCrypt.checkpw(senha, hashSenha)) {
//            System.out.println("Senha correta!");
//            return senha;
//        } else {
//            System.out.println("Senha incorreta!/n digite novamente");
//            senha = scanner.next();
//            return checkSenha(senha, scanner);
//        }
//
//    }


}
