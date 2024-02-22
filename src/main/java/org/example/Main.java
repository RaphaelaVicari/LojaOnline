package org.example;

import org.example.model.Cliente;
import org.example.model.Produto;
import org.example.model.Venda;
import org.example.repository.ClienteRepository;
import org.example.repository.ProdutoRepository;
import org.example.repository.VendaRepository;
import org.example.security.PasswordSecurity;
import org.example.service.ClienteService;
import org.example.util.Constantes;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

	
    public static void main(String[] args) {
    	
    	Scanner scanner = new Scanner(System.in);

        Cliente cliente = new Cliente();
        cliente.setNomeCliente("breno");
        cliente.setEmailCliente("breno@gmail.com");
        cliente.setDataNascimentoCliente("12/02/2000");
        cliente.setEnderecoCliente("Rua Teste 123");
        cliente.setCpfCliente("831.429.290-70");
        cliente.setSenhaCliente("senha@senha");
        cliente.setNumeroCelularCliente("(11) 93589-8989");
        cliente.setSenhaCliente("nome");

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

        //Scanner scanner;
		int menuNum = scanner.nextInt();

        switch (menuNum) {

            case 1:
            	//Cliente cliente = new Cliente();

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

            case 2:
            case 3:
            case 4:
            case 9:

        }
    }
}

