package org.example.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordSecurity {
    public String encriptarSenha(String senha) {

        //senha que sera encriptada
        var hashSenha = BCrypt.hashpw(senha, BCrypt.gensalt());
        return hashSenha;
    }

    public void checkSenha(String senha) {
        //forma de verificar a senha
        var hashSenha = encriptarSenha(senha);
        if (BCrypt.checkpw(senha, hashSenha)) {
            System.out.println("Senha correta!");
        } else {
            System.out.println("Senha incorreta!");
        }
    }
}
