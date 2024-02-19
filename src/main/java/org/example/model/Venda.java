package org.example.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class Venda {

    public Venda() {

    }

    public Venda(Cliente cliente) {
        listaProdutos = new ArrayList<>();
        valorTotalVenda = 0;
        clienteVenda = cliente;
    }

    private List<VendaProduto> listaProdutos;
    private Cliente clienteVenda;
    private double valorTotalVenda;

    public void adicionarProduto(Produto produtoAdicionado) {
        VendaProduto produtoVendido = new VendaProduto(produtoAdicionado);
        valorTotalVenda += produtoVendido.getPrecoProduto();
        listaProdutos.add(produtoVendido);
    }

    public VendaProduto removerProduto(int produtoAdicionado) {
        VendaProduto produtoRemovido = listaProdutos.remove(produtoAdicionado);
        valorTotalVenda -= produtoRemovido.getPrecoProduto();
        return produtoRemovido;
    }


    public double getValorTotalVenda() {
        return valorTotalVenda;
    }

    public Cliente getClienteVenda() {
        return clienteVenda;
    }

    public List<VendaProduto> getListaProdutos() {
        return listaProdutos;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "listaProdutos=" + listaProdutos +
                ", clienteVenda=" + clienteVenda +
                ", valorTotalVenda=" + valorTotalVenda +
                '}';
    }
}
