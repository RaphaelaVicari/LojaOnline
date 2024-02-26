package org.example.security;

import org.example.repository.ClienteRepository;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordSecurity {
    private ClienteRepository clienteRepository;


    public String encriptarSenha(String senha) {

        //senha que sera encriptada
        var hashSenha = BCrypt.hashpw(senha, BCrypt.gensalt());
        return hashSenha;
    }

    public boolean checkSenha(String senha, String senhaHash) {
        //forma de verificar a senha
        return BCrypt.checkpw(senha, senhaHash);
    }


}
