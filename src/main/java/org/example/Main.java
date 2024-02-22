package org.example;
import org.example.model.Cliente;
import org.example.model.Produto;
import org.example.model.Venda;
import org.example.repository.ClienteRepository;
import org.example.repository.ProdutoRepository;
import org.example.repository.VendaRepository;
import org.example.service.ClienteService;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Cliente cliente = new Cliente();
        cliente.setNomeCliente("Marco");
        cliente.setEmailCliente("marco@gmail.com");
        cliente.setDataNascimentoCliente("08/08/1997");
        cliente.setEnderecoCliente("Rua Teste 123");
        cliente.setCpfCliente("839.149.310-51");
        cliente.setSenhaCliente("senha@senha");
        cliente.setNumeroCelularCliente("921212121");
        cliente.setSenhaCliente("nome");

        ClienteService clienteRepository = new ClienteService();
        clienteRepository.clienteNovo(cliente);

       

    }
}
