package org.example.util;

public class Constantes {
    public static final String bemVindo = "BEM VINDO AO LOJÃO DO R$1,99!!";

    public static final String menu = "Opções:\n 1- Criar conta\n 2- Ver Lista de Produtos\n 3- Cadastrar Produtos\n 4- Verificar status de conta\n 9- Encerrar Programa";

    public static final String cadastroClienteNome = "Insira o nome";

    public static final String cadastroClienteCpf = "Insira um CPF válido (incluir pontuação - ex: 111.111.111-11)";

    public static final String cadastroClienteData = "Insira a data de nascimento (incluir pontuação - ex: 11/09/2001)";

    public static final String cadastroClienteEmail = "Insira um Email válido";

    public static final String cadastroClienteCelular = "Insira o número de celular (apenas números)";

    public static final String cadastroClienteEndereco = "Insira o endereço";

    public static final String cadastroClienteSenha = "Crie uma senha";

    public static final String cadastroClienteSenhaConfirma = "Confirme sua senha";

    public static final String StatusClienteCpf = "Digite o CPF";

    public static final String StatusClienteSenha = "Digite a Senha";


    public static void ABERTURADALOJA() {
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 30; j++) {
                if (i == 1) {
                    System.out.println(bemVindo);
                    i = 2;
                }
                System.out.print('#');
            }
            System.out.println();
        }

        System.out.println(menu);

    }

}
