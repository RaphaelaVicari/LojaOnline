package org.example.model;

import java.util.ArrayList;
import java.util.List;

import static org.example.util.FuncoesUtil.formatarColuna;

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

    public VendaProduto removerProduto(int codigoProduto) {
        VendaProduto vendaProduto = procurarVendaProduto(codigoProduto);

        if (vendaProduto == null) {
            return null;
        }

        listaProdutos.remove(vendaProduto);
        valorTotalVenda -= vendaProduto.getPrecoProduto();
        return vendaProduto;
    }

    private VendaProduto procurarVendaProduto(int codigoProduto) {
        for (VendaProduto vendaProduto : listaProdutos) {
            if (vendaProduto.getCodigoProduto() == codigoProduto) {
                return vendaProduto;
            }
        }
        return null;
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

    public void listarProdutosVenda() {
        String cabecalho = formatarColuna("ID", 3) +
                formatarColuna("PRODUTO", 41) +
                formatarColuna("PREÇO UNIDADE", 20) +
                formatarColuna("DESCRIÇÃO DO PRODUTO", 44);

        System.out.println(cabecalho);

        for (VendaProduto dados : listaProdutos) {
            System.out.println(String.format("%s%s%sR$ %s",
                    formatarColuna(String.valueOf(dados.getCodigoProduto()), 3),
                    formatarColuna(dados.getNomeProduto(), 41),
                    formatarColuna(String.valueOf(dados.getPrecoProduto()), 17),
                    formatarColuna(dados.getDescricaoProduto(), 44)));
        }

        System.out.printf("TOTAL: %.2f\n", getValorTotalVenda());
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
