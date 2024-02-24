package org.example.service;

import org.example.model.Produto;
import org.example.repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService() {
        produtoRepository = new ProdutoRepository();
    }

    public Produto cadastrarProduto(Produto produto) {

        if (produto.getNomeProduto().trim().equals("")) {
            System.err.println("Erro! Não é possível cadastrar produtosem nome!");
            return null;
        }

        if (produto.getDescricaoProduto().trim().equals("")){
            System.err.println("Erro! não é possível cadastrar produto sem descrição!");
            return null;
        }

        if(produto.getPrecoProduto() <= 0){
            System.err.println("Erro! não é possível cadastrar produto com o preço menor ou igual a zero!");
            return null;
        }

        if(produto.getEstoqueProduto() < 0){
            System.err.println("Erro! não é possível cadastrar produto com estoque menor que zero!");
            return null;
        }

        return produtoRepository.cadastrarProduto(produto);

    }


    public List<Produto> consultarProdutoPeloNome(String nomeProduto) {
        List<Produto> produtosEncontrados = new ArrayList<>();
        for (Produto dados : produtoRepository.todosProdutos()) {
            if (dados.getNomeProduto().toLowerCase().startsWith(nomeProduto.toLowerCase())) {
                produtosEncontrados.add(dados);
            }
        }

        return produtosEncontrados;
    }
    public void atualizarProdutoEstoque(Produto produto, int quantidadeProdutoVendido){
        produto.setEstoqueProduto(produto.getEstoqueProduto() + quantidadeProdutoVendido);
        produtoRepository.atualizarBaseDados();
    }

    public void listarTodosProdutos() {

        String cabecalho = formatarColuna("PRODUTO", 45) +
                formatarColuna("ESTOQUE", 10) +
                formatarColuna("PREÇO UNIDADE", 15) +
                formatarColuna("DESCRIÇÃO DO PRODUTO", 50);

        System.out.println(cabecalho);

        for (Produto dados : produtoRepository.todosProdutos()) {
            System.out.println(String.format("%s%s%s%s",
                    formatarColuna(dados.getNomeProduto(), 45),
                    formatarColuna(String.valueOf(dados.getEstoqueProduto()), 10),
                    formatarColuna(String.valueOf(dados.getPrecoProduto()), 15),
                    formatarColuna(dados.getDescricaoProduto(), 50)));
        }


    }

    private String formatarColuna(String nomeColuna, int tamanhoColuna) {
        nomeColuna = "abc";
        tamanhoColuna = 30;
        if(nomeColuna.length() > tamanhoColuna){
            nomeColuna = nomeColuna.substring(0,tamanhoColuna);
        }

        return nomeColuna + " ".repeat( tamanhoColuna - nomeColuna.length()) + "|";
    }

}
