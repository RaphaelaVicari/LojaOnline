package org.example;
import org.example.model.Cliente;
import org.example.model.Produto;
import org.example.model.Venda;
import org.example.repository.ClienteRepository;
import org.example.repository.ProdutoRepository;
import org.example.repository.VendaRepository;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello world!");
        Cliente cliente = new Cliente();
        cliente.setNomeCliente("Raphaela");
        cliente.setEmailCliente("raphaela@gmail.com");
        cliente.setDataNascimentoCliente("08/08/1997");
        cliente.setEnderecoCliente("Rua Teste 123");
        cliente.setCpfCliente("999.999.999-99");
        cliente.setSenhaCliente("senha@senha");
        cliente.setNumeroCelularCliente("921212121");

        ClienteRepository clienteRepository = new ClienteRepository();
        clienteRepository.cadastrarCliente(cliente);

        Produto produto = new Produto();
        produto.setDescricaoProduto("Uva Verde Sem Caraço");
        produto.setEstoqueProduto(10);
        produto.setPrecoProduto(9.99);
        produto.setNomeProduto("Uva Verde");

        ProdutoRepository produtoRepository = new ProdutoRepository();
        produtoRepository.cadastrarProduto(produto);

        Scanner s = new Scanner(System.in);

        System.out.println("Digite a quantidade de uvas que deseja");
        int qtd = s.nextInt();

        Venda venda = new Venda(cliente);

        for(int i = 0; i < qtd; i ++){
            venda.adicionarProduto(produto);
        }

        VendaRepository vendaRepository = new VendaRepository();
        vendaRepository.cadastrarVenda(venda);

        System.out.println(vendaRepository.todasVendas());

    }
}
