package org.example.service;

import org.example.model.Produto;

public class EstoqueService {

    private ProdutoService produtoService;

    public EstoqueService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public boolean removerEstoque(Produto produtoRemovido){
        if(produtoRemovido.getEstoqueProduto() == 0){
            return false;
        }

        produtoService.atualizarProdutoEstoque(produtoRemovido, -1);
        return true;
    }

    public boolean alterarQuantidadeEstoque(long idProduto, int quantidadeEstoqueAlterar) {
        if (quantidadeEstoqueAlterar <= 0) {
            System.err.println("Não é possível definir quantidade de estoque negativo ou zero!");
            return false;
        }

        Produto consultarProdutoPeloId = produtoService.consultarProdutoPeloId(idProduto);

        if (consultarProdutoPeloId == null) {
            System.err.println("ID do produto não encontrado!");
            return false;
        }

        produtoService.definirProdutoEstoque(consultarProdutoPeloId, quantidadeEstoqueAlterar);
        return true;
    }

    public boolean adicionarEstoque(long codigoProduto) {
        Produto p = produtoService.consultarProdutoPeloId(codigoProduto);

        if (p == null) {
            System.err.println("Produto não encontrado para atualizar o estoque");
            return false;
        }

        produtoService.atualizarProdutoEstoque(p, 1);
        return true;
    }

}

