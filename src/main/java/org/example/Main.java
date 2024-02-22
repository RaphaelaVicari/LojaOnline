package org.example;

import org.example.model.Cliente;
import org.example.service.ClienteService;
import org.example.service.ProdutoService;
import org.example.util.Constantes;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoService produtoService = new ProdutoService();

        ClienteService clienteRepository = new ClienteService();

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
        //        clienteRepository.clienteNovo(cliente);

        Constantes.ABERTURADALOJA();

        int menuNum = scanner.nextInt();

        switch (menuNum) {

            case 1:
                Cliente cliente = new Cliente();

                System.out.println(Constantes.cadastroClienteNome);
                cliente.setNomeCliente(scanner.next());

                System.out.println(Constantes.cadastroClienteCpf);
                cliente.setCpfCliente(scanner.next());

                System.out.println(Constantes.cadastroClienteData);
                cliente.setDataNascimentoCliente(scanner.next());

                System.out.println(Constantes.cadastroClienteEmail);
                cliente.setEmailCliente(scanner.next());

                System.out.println(Constantes.cadastroClienteCelular);
                cliente.setNumeroCelularCliente(scanner.next());

                System.out.println(Constantes.cadastroClienteEndereco);
                cliente.setEnderecoCliente(scanner.next());

                System.out.println(Constantes.cadastroClienteSenha);
                String senha = scanner.next();

                System.out.println(Constantes.cadastroClienteSenhaConfirma);
                String senhaConfirm = scanner.next();

                clienteRepository.cadastroSenha(scanner, senha, senhaConfirm);
                clienteRepository.clienteNovo(cliente);

                break;
            case 2:
                produtoService.listarTodosProdutos();
                break;
            case 3:
            case 4:
            case 9:


        }


    }
}

