package org.example.service;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import org.example.model.Cliente;
import org.example.repository.ClienteRepository;


import org.example.security.PasswordSecurity;
import org.mindrot.jbcrypt.BCrypt;

public class ClienteService {

    private ClienteRepository clienteRepository;
    private PasswordSecurity passwordSecurity;

    public ClienteService() {
        this.clienteRepository = new ClienteRepository();
    }

    public static class CPF {
        private String cpf;
        private static final String Formato = "###.###.###-##";

        public CPF(String C) {
            this.cpf = this.Format(C, false);
        }

        public boolean isCPF() {

            if (this.cpf.equals("00000000000") ||
                    this.cpf.equals("11111111111") ||
                    this.cpf.equals("22222222222") ||
                    this.cpf.equals("33333333333") ||
                    this.cpf.equals("44444444444") ||
                    this.cpf.equals("55555555555") ||
                    this.cpf.equals("66666666666") ||
                    this.cpf.equals("77777777777") ||
                    this.cpf.equals("88888888888") ||
                    this.cpf.equals("99999999999") ||
                    this.cpf.length() != 11)
                return (false);

            char dig10, dig11;
            int sm, i, r, num, peso;

            try {
                // Calculo do primeiro Digito Verificador 
                sm = 0;
                peso = 10;
                for (i = 0; i < 9; i++) {
                    num = (int) (this.cpf.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }
                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig10 = '0';
                else
                    dig10 = (char) (r + 48);

                // Calculo do segundo Digito Verificador 
                sm = 0;
                peso = 11;
                for (i = 0; i < 10; i++) {
                    num = (int) (this.cpf.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }
                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig11 = '0';
                else
                    dig11 = (char) (r + 48);

                if ((dig10 == this.cpf.charAt(9)) && (dig11 == this.cpf.charAt(10)))
                    return (true);
                else return (false);
            } catch (Exception e) {
                return (false);
            }
        }

        public String getCPF(boolean Mascara) {
            return Format(this.cpf, Mascara);
        }

        private String Format(String C, boolean Mascara) {
            if (Mascara) {
                return (C.substring(0, 3) + "." + C.substring(3, 6) + "." +
                        C.substring(6, 9) + "-" + C.substring(9, 11));
            } else {
                C = C.replace(".", "");
                C = C.replace("-", "");
                return C;
            }
        }
                
        public DefaultFormatterFactory getFormat(){
            try {
                return new DefaultFormatterFactory(new MaskFormatter(Formato));
            } catch (Exception e) {
                return null;
            }
        }
    }

    public Cliente clienteNovo(Cliente cliente) {
        cliente.setSaldo(100);

        cliente.setSenhaCliente( passwordSecurity.encriptarSenha(cliente.getSenhaCliente()));
        return clienteRepository.cadastrarCliente(cliente);

    }    
 
    public boolean validarEmail(String email) {
        // Define a expressão regular para validar o formato do e-mail
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compila a expressão regular
        Pattern pattern = Pattern.compile(regex);

        // Cria um objeto Matcher para o e-mail fornecido
        Matcher matcher = pattern.matcher(email);

        // Verifica se o e-mail corresponde ao padrão definido
        return matcher.matches();
    }
}


