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

    //TODO implementar regras de negócio dentro desse método após validaçoes do produto persistir ele com o repository
    public Produto cadastrarProduto(Produto produto) {
        System.out.println("Hello World");
        System.out.println("Hello World");
        System.out.println("Hello World");
        System.out.println("Hello World");
        System.out.println("Hello World");
        return null;
    }

    public List<Produto> consultarProdutoPeloNome(String nomeProduto){
        List<Produto> produtosEncontrados = new ArrayList<>();
        for(Produto dados : produtoRepository.todosProdutos()){
            if (dados.getNomeProduto().toLowerCase().startsWith(nomeProduto.toLowerCase())){
                produtosEncontrados.add(dados);
            }
        }
        return produtosEncontrados;
    }

    public void listarTodosProdutos(){
        for(Produto dados : produtoRepository.todosProdutos()){
            System.out.println(dados.toString());
        }
    }

}
