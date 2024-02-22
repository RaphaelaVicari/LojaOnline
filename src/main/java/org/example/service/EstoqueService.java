package org.example.service;

import org.example.model.Produto;

public class EstoqueService {

    private ProdutoService produtoService;

    public  EstoqueService(){
        this.produtoService = new ProdutoService();
    }

    public boolean removerEstoque(Produto produtoRemovido){
        if(produtoRemovido.getEstoqueProduto() == 0){
            System.err.println("Erro, estoque indisponível! ");
            return false;
        }
        produtoService.atualizarProdutoEstoque(produtoRemovido, -1);
        return true;
    }

    public void adicionarEstoque(Produto produtoAdicionado){
        produtoService.atualizarProdutoEstoque(produtoAdicionado, 1);
    }

}

