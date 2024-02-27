package org.example;

import org.example.model.Cliente;
import org.example.model.Produto;
import org.example.model.Venda;
import org.example.model.VendaProduto;
import org.example.security.PasswordSecurity;
import org.example.service.ClienteService;
import org.example.service.EstoqueService;
import org.example.service.ProdutoService;
import org.example.service.VendaService;
import org.example.util.Constantes;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoService produtoService = new ProdutoService();
        ClienteService clienteService = new ClienteService();
        EstoqueService estoqueService = new EstoqueService(produtoService);
        VendaService vendaService = new VendaService(clienteService);

        boolean lojaRodando = true;

        Constantes.aberturaDaLoja();

        while (lojaRodando) {
            System.out.println(Constantes.menu);
            String menu = scanner.nextLine();
            int menuNum = Integer.parseInt(menu);
            switch (menuNum) {
               
                case 1:

                    Cliente cliente = new Cliente();

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
                    if(clienteService.clienteNovo(cliente)== null){
                        System.err.println("Erro! Não foi possivel cadastrar o cliente!");
                        break;
                    }
                    System.out.println("Cliente cadastrado com sucesso");
                    break;
                //lista de produtos
                case 2:
                    produtoService.listarTodosProdutos();
                    break;

                //status da conta
                case 3:

                    System.out.println(Constantes.statusCliente);

                    cliente = pegarClienteCadastrado(scanner, clienteService);
                    if (cliente == null)
                        break;

                    if (!confirmarSenhaCliente(clienteService, cliente, scanner))
                        break;

                    System.out.println("Senha correta!");

                    String cabecalho = "NOME CLIENTE: " + cliente.getNomeCliente() + "\n" +
                            "CPF: " + cliente.getCpfCliente() + "\n" +
                            "SALDO: R$" + String.format("%.2f", cliente.getSaldo()) + "\n" +
                            "ENDEREÇO: " + cliente.getEnderecoCliente() + "\n" +
                            "NÚMERO DE CELULAR: " + cliente.getNumeroCelularCliente() + "\n" +
                            "E-MAIL: " + cliente.getEmailCliente() + "\n" +
                            "DATA DE NASCIMENTO: " + cliente.getDataNascimentoCliente();
                    System.out.println(cabecalho);
                    break;

                //giftCard
                case 4:
                    comprarGiftCard(scanner, clienteService);
                    break;
                case 5:
                    cliente = pegarClienteCadastrado(scanner, clienteService);

                    if (cliente == null) {
                        break;
                    }

                    Venda venda = new Venda(cliente);

                    while (true) {
                        System.out.println("(1) Adicionar Produtos");
                        System.out.println("(2) Remover Produtos");
                        System.out.println("(3) Listar Carrinho");
                        System.out.println("(4) Finalizar Venda");
                        System.out.println("(5) Comprar GiftCard");
                        System.out.println("(6) Voltar para o menu anterior");

                        System.out.printf("SALDO: %.2f\n", cliente.getSaldo());

                        int escolhaVenda = Integer.parseInt(scanner.nextLine());

                        if (escolhaVenda == 4) {
                            venda.listarProdutosVenda();

                            if (!confirmarSenhaCliente(clienteService, venda.getClienteVenda(), scanner))
                                continue;

                            if (vendaService.cadastrarVenda(venda) == null) {
                                continue;
                            }

                            System.out.println("COMPRA EFETUADA COM SUCESSO!");
                            break;
                        }

                        if (escolhaVenda == 6) {
                            for (VendaProduto i : venda.getListaProdutos()) {
                                estoqueService.adicionarEstoque(i.getCodigoProduto());
                            }
                            break;
                        }

                        switch (escolhaVenda) {
                            case 1:

                                while (true) {
                                    produtoService.listarTodosProdutos();

                                    System.out.println("Escolha o ID do produto que deseja adicionar ao carrinho");
                                    System.out.println("Para sair digite -1");
                                    int idProduto = Integer.parseInt(scanner.nextLine());

                                    if (idProduto == -1) {
                                        break;
                                    }

                                    Produto produto = produtoService.consultarProdutoPeloId(idProduto);
                                    if (produto == null) {
                                        System.err.println("Erro id produto não existente");
                                        continue;
                                    }

                                    if (!estoqueService.removerEstoque(produto)) {
                                        System.err.println("Erro, estoque indisponível!");
                                        continue;
                                    }

                                    venda.adicionarProduto(produto);
                                }
                                break;
                            case 2:
                                removerProdutoVenda(scanner, estoqueService, venda);
                                break;
                            case 3:
                                venda.listarProdutosVenda();
                                break;
                            case 5:
                                comprarGiftCard(scanner, clienteService);
                                break;
                            default:
                                System.err.println("Opção Invalida");
                        }

                    }
                    
                    break;

                case 6:
                    menuAdministrativo(scanner, produtoService, estoqueService);
                    break;
                //encerrar programa
                case 9:
                    lojaRodando = false;
                    System.out.println("Programa encerrado!!!");
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    private static void menuAdministrativo(Scanner scanner, ProdutoService produtoService, EstoqueService estoqueService) {
        String login = "adm2024";
        String senha = "$2a$10$13/trw5mFS/gZ6BHmjIzQeFf7xYO8u7yFRyWAKrS7BGeFzMGHVJvm";
        PasswordSecurity autenticadorSenha = new PasswordSecurity();

        System.out.println("Digite o login administrativo");
        String loginAdm = scanner.nextLine();

        System.out.println("Digite a senha administrativa");
        String senhaAdm = scanner.nextLine();

        if (!login.equals(loginAdm) || !autenticadorSenha.checkSenha(senhaAdm, senha)) {
            System.err.println("Erro usuário ou senha incorreto!");
            return;
        }

        while (true) {
            System.out.println("(1) Cadastrar novo produto");
            System.out.println("(2) Remover produto");
            System.out.println("(3) Alterar quantidade de estoque");
            System.out.println("(4) Voltar para menu");

            int lerOpcao = Integer.parseInt(scanner.nextLine());
            switch (lerOpcao) {

                case 1:
                    cadastrarNovoProdutoAdm(scanner, produtoService);
                    break;
                case 2:
                    produtoService.listarTodosProdutos();
                    System.out.println("Digite o ID do produto que deseja remover!");
                    long codigoDoProduto = Long.parseLong(scanner.nextLine());

                    if (produtoService.removerProduto(codigoDoProduto) == null) {
                        System.err.println("Erro ao remover produto!");
                        break;
                    }
                    System.out.println("Produto removido com sucesso!");
                    break;
                case 3:
                    produtoService.listarTodosProdutos();
                    System.out.println("Digite o ID do produto que deseja alterar a quantidade de estoque!");
                    long codigo = Long.parseLong(scanner.nextLine());

                    System.out.println("Digite a quantidade do estoque que deseja atribuir ao produto");
                    int estoqueQuantidadeAlterada = Integer.parseInt(scanner.nextLine());

                    if (!estoqueService.alterarQuantidadeEstoque(codigo, estoqueQuantidadeAlterada)) {

                        System.err.println("Erro, não foi possível realizar alteração no estoque do produto!");
                        break;
                    }
                    System.out.println("Alteração do estoque realizada com sucesso!");
                    break;
                case 4:
                    return;
                default:
                    System.err.println("Erro, opção inválida!");
            }
        }

    }

    private static void cadastrarNovoProdutoAdm(Scanner scanner, ProdutoService produtoService) {
        boolean cadastrandoProduto = true;
        while (cadastrandoProduto) {
            Produto produto = new Produto();

            System.out.println(Constantes.cadastroProdutoCodigo);
            produto.setCodigoProduto(Long.parseLong(scanner.nextLine()));

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

            System.out.println("Novo produto adicionado com sucesso!");
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
    }

    private static void comprarGiftCard(Scanner scanner, ClienteService clienteService) {
        Cliente cliente;
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
                System.err.println("Opção inválida");
                break;

        }
        if (menuGiftNum >= 1 && menuGiftNum <= 5) {
            System.out.println("R$" + giftCardValor + " serão adicionados na sua conta.\nPara confirmar, insira as informações abaixo:");
            cliente = pegarClienteCadastrado(scanner, clienteService);
            if (cliente == null)
                return;

            if (!confirmarSenhaCliente(clienteService, cliente, scanner))
                return;

            double clienteSaldo = cliente.getSaldo();
            clienteService.atualizarClienteSaldo(cliente, clienteSaldo += giftCardValor);

            System.out.println("Adicionado Saldo!");
            System.out.printf("Saldo Atual: %.2f \n", clienteSaldo);
        }
    }

    private static void removerProdutoVenda(Scanner scanner,
                                            EstoqueService estoqueService,
                                            Venda venda) {
        while (true) {
            venda.listarProdutosVenda();

            System.out.println("Escolha o ID do produto que deseja remover do carrinho");
            System.out.println("Para sair digite -1");
            int idProduto = Integer.parseInt(scanner.nextLine());

            if (idProduto == -1) {
                break;
            }

            VendaProduto vendaProduto = venda.removerProduto(idProduto);
            if (vendaProduto == null) {
                System.err.println("Esse produto não existe no carrinho");
                continue;
            }

            if (!estoqueService.adicionarEstoque(vendaProduto.getCodigoProduto())) {
                System.err.println("Erro, não foi possivel adicionar produto ao estoque");
            }
        }
    }

    private static void adicionarProdutosVenda(ProdutoService produtoService, Scanner scanner, EstoqueService estoqueService, Venda venda) {
        Produto produto;

    }

    private static boolean confirmarSenhaCliente(ClienteService clienteService, Cliente cliente, Scanner scanner) {
        System.out.println(Constantes.clienteSenha);
        if (!clienteService.checkSenha(cliente, scanner.nextLine())) {
            System.err.println("Senha incorreta \n digite novamente ");
            if (!clienteService.checkSenha(cliente, scanner.nextLine())) {
                System.err.println("Senha incorreta\n operação cancelada");
                return false;
            }

        }
        return true;
    }

    private static Cliente pegarClienteCadastrado(Scanner scanner,
                                                  ClienteService clienteService) {
        Cliente cliente;
        System.out.println(Constantes.clienteCpf);
        String cpf = scanner.nextLine();
        if (!clienteService.cpfJaCadastrado(cpf)) {
            System.err.println("CPF não cadastrado");
            return null;
        }
        cliente = clienteService.consultarClientePorCpf(cpf);
        return cliente;
    }
}

