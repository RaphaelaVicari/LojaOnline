package org.example.util;

public class Constantes {
    public static final String bemVindo = "BEM VINDO AO LOJÃO DO R$1,99!!";

    public static final String menu = "Opções:\n 1- Criar conta\n 2- Ver Lista de Produtos\n 3- Cadastrar Produtos\n 4- Verificar status de conta\n 5- Adicionar fundos na conta \n 9- Encerrar Programa";

    public static final String cadastroClienteNome = "Insira o nome";

    public static final String cadastroClienteCpf = "Insira um CPF válido (incluir pontuação - ex: 111.111.111-11)";

    public static final String cadastroClienteData = "Insira a data de nascimento (incluir pontuação - ex: 11/09/2001)";

    public static final String cadastroClienteEmail = "Insira um Email válido";

    public static final String cadastroClienteCelular = "Insira o número de celular (incluir pontuação) - ex: (DDD)91010-2020";

    public static final String cadastroClienteEndereco = "Insira o endereço";

    public static final String cadastroClienteSenha = "Crie uma senha";

    public static final String cadastroClienteSenhaConfirma = "Confirme sua senha";

    public static final String statusCliente = "Preencha os campos abaixo para verificar o status da conta";

    ///status da conta////
    public static final String clienteCpf = "Digite o CPF";

    public static final String clienteSenha = "Digite a Senha";

    ////// cadastro de produto //////

    public static final String cadastroProdutoNome = "Insira o nome do produto";

    public static final String cadastroProdutoDescricao = "Insira a descrição do produto";

    public static final String cadastroProdutoValor = "Insira o valor do produto";

    public static final String cadastroProdutoEstoque = "Insira o valor de estoque do produto";

    ///// gift card /////

    public static final String compraGiftCard = "Escolha uma opção para adicionar fundos na sua conta\n 1- R$10,00\n 2- R$25,00\n 3- R$50,00\n 4- R$100,00\n 5- R$250,00\n 9- voltar para o menu";

   public  static final  String adicionarFundos = "Deseja adicionar R$";

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
