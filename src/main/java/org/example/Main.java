package org.example;

import org.example.model.Cliente;
import org.example.model.Produto;
import org.example.service.ClienteService;
import org.example.service.ProdutoService;
import org.example.util.Constantes;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoService produtoService = new ProdutoService();
        ClienteService clienteService = new ClienteService();

        boolean lojaRodando = true;
        Cliente cliente = new Cliente();
        Produto produto = new Produto();

        //        Cliente cliente = new Cliente();
        //        cliente.setNomeCliente("Marco");
        //        cliente.setEmailCliente("marco@gmail.com");
        //        cliente.setDataNascimentoCliente("08/08/1997");
        //        cliente.setEnderecoCliente("Rua Teste 123");
        //        cliente.setCpfCliente("839.149.310-51");
        //        cliente.setSenhaCliente("senha@senha");
        //        cliente.setNumeroCelularCliente("921212121");
        //        cliente.setSenhaCliente("nome");
        //
        //        clienteService.clienteNovo(cliente);

        while (lojaRodando) {
            Constantes.ABERTURADALOJA();

            String menu = scanner.nextLine();
            int menuNum = Integer.parseInt(menu);
            switch (menuNum) {
                //cadastro de cliente
                case 1:

                    System.out.println(Constantes.cadastroClienteNome);
                    cliente.setNomeCliente(scanner.nextLine());

                    System.out.println(Constantes.cadastroClienteCpf);
                    cliente.setCpfCliente(scanner.nextLine());

                    System.out.println(Constantes.cadastroClienteData);
                    cliente.setDataNascimentoCliente(scanner.nextLine());

                    System.out.println(Constantes.cadastroClienteEmail);
                    cliente.setEmailCliente(scanner.nextLine());

                    System.out.println(Constantes.cadastroClienteCelular);
                    cliente.setNumeroCelularCliente(scanner.nextLine());

                    System.out.println(Constantes.cadastroClienteEndereco);
                    cliente.setEnderecoCliente(scanner.nextLine());

                    System.out.println(Constantes.cadastroClienteSenha);
                    String senha = scanner.nextLine();

                    System.out.println(Constantes.cadastroClienteSenhaConfirma);
                    String senhaConfirm = scanner.nextLine();

                    cliente.setSenhaCliente(clienteService.cadastroSenha(scanner, senha, senhaConfirm));
                    clienteService.clienteNovo(cliente);

                    break;

                //lista de produtos
                case 2:
                    produtoService.listarTodosProdutos();
                    break;

                //cadastrar produto
                case 3:

                    boolean cadastrandoProduto = true;
                    while (cadastrandoProduto) {
                        System.out.println(Constantes.cadastroProdutoNome);
                        produto.setNomeProduto(scanner.nextLine());

                        System.out.println(Constantes.cadastroProdutoDescricao);
                        produto.setDescricaoProduto(scanner.nextLine());

                        System.out.println(Constantes.cadastroProdutoValor);
                        String produtoValor = scanner.nextLine();
                        double valor = Double.parseDouble(produtoValor);
                        produto.setPrecoProduto(valor);

                        System.out.println(Constantes.cadastroProdutoEstoque);
                        String produtoEstoque = scanner.nextLine();
                        int estoque = Integer.parseInt(produtoEstoque);
                        produto.setEstoqueProduto(estoque);

                        produtoService.cadastrarProduto(produto);

                        System.out.println("Deseja adicionar um novo produto? \n 0- Não \n 1- Sim");

                        boolean repetirCadastro = true;
                        while (repetirCadastro) {
                            int novoProduto = Integer.parseInt(scanner.nextLine());
                            if (novoProduto < 0 || novoProduto > 1) {
                                System.out.println("Opção inválida");

                            }
                            if (novoProduto == 0) {
                                cadastrandoProduto = false;
                                repetirCadastro = false;
                            }
                            if (novoProduto == 1) {
                                repetirCadastro = false;
                            }

                        }


                    }
                    break;

                //status da conta
                case 4:

                    System.out.println(Constantes.statusCliente);

                    System.out.println(Constantes.clienteCpf);
                    String cpf = scanner.nextLine();
                    if (!clienteService.cpfJaCadastrado(cpf)) {
                        System.err.println("CPF não cadastrado");
                        break;
                    }
                    cliente = clienteService.consultarClientePorCpf(cpf);

                    System.out.println(Constantes.clienteSenha);

                    if (!clienteService.checkSenha(cliente, scanner.nextLine())) {
                        System.err.println("Senha incorreta \n digite novamente ");
                        if (!clienteService.checkSenha(cliente, scanner.nextLine())) {
                            System.err.println("Senha incorreta\n operação cancelada");
                            break;
                        }

                    }
                    System.out.println("Senha correta!");
                    System.out.println(cliente.toString());

                    break;

                //giftCard
                case 5:
                    System.out.println(Constantes.compraGiftCard);
                    String menuGift = scanner.nextLine();
                    int menuGiftNum = Integer.parseInt(menuGift);
                    double giftCardValor = 0;

                    switch (menuGiftNum) {

                        case 1:
                            giftCardValor = 10;
                            break;

                        case 2:
                            giftCardValor = 20;
                            break;
                        case 3:
                            giftCardValor = 50;
                            break;
                        case 4:
                            giftCardValor = 100;
                            break;
                        case 5:
                            giftCardValor = 250;
                            break;
                        case 9:
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;

                    }
                    if (menuGiftNum >= 1 && menuGiftNum <= 5) {
                        System.out.println("R$" + giftCardValor + " serão adicionados na sua conta.\nPara confirmar, insira as informações abaixo:");
                        System.out.println(Constantes.clienteCpf);
                        cpf = scanner.nextLine();
                        if (!clienteService.cpfJaCadastrado(cpf)) {
                            System.err.println("CPF não cadastrado");
                            break;
                        }
                        cliente = clienteService.consultarClientePorCpf(cpf);

                        System.out.println(Constantes.clienteSenha);

                        if (!clienteService.checkSenha(cliente, scanner.nextLine())) {
                            System.err.println("Senha incorreta \n digite novamente ");
                            if (!clienteService.checkSenha(cliente, scanner.nextLine())) {
                                System.err.println("Senha incorreta\n operação cancelada");
                                break;
                            }

                        }
                        double clienteSaldo = cliente.getSaldo();
                       clienteService.atualizarClienteSaldo(cliente,clienteSaldo+= giftCardValor);
                    }


                    break;

                //encerrar programa
                case 9:
                    lojaRodando = false;
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }


        }


    }
}

