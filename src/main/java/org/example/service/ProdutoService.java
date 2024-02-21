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
        produto.setEstoqueProduto(produto.getEstoqueProduto() - quantidadeProdutoVendido);
        produtoRepository.atualizarBaseDados();
    }

    public void listarTodosProdutos() {
        for (Produto dados : produtoRepository.todosProdutos()) {
            System.out.println(dados.toString());
        }
    }
}
