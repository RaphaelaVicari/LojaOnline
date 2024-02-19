package org.example;
import org.example.model.Cliente;
import org.example.repository.ClienteRepository;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Cliente cliente = new Cliente();

        cliente.setNomeCliente("Raphaela");
        cliente.setEmailCliente("raphaela@gmail.com");
        cliente.setDataNascimentoCliente("08/08/1997");
        cliente.setEnderecoCliente("Rua Teste 123");
        cliente.setCpfCliente("999.999.999-99");
        cliente.setSenhaCliente("senha@senha");
        cliente.setNumeroCelularCliente("912121212");

        ClienteRepository clienteRepository = new ClienteRepository();
        clienteRepository.cadastrarCliente(cliente);

    }
}
