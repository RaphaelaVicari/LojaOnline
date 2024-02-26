package org.example.service;

import org.example.model.Produto;
import org.example.repository.ProdutoRepository;

import static org.example.util.FuncoesUtil.formatarColuna;

public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService() {
        produtoRepository = new ProdutoRepository();
    }

    public Produto cadastrarProduto(Produto produto) {

        if (consultarProdutoPeloId(produto.getCodigoProduto()) != null) {
            System.err.println("Erro! Produto com esse código de ID já está cadastrado");
            return null;
        }

        if (produto.getCodigoProduto() <= 0) {
            System.err.println("Erro! Não é possível cadastrar produto com código inválido!");
            return null;
        }

        if (produto.getNomeProduto().trim().equals("")) {
            System.err.println("Erro! Não é possível cadastrar produto sem nome!");
            return null;
        }

        if (produto.getDescricaoProduto().trim().equals("")) {
            System.err.println("Erro! não é possível cadastrar produto sem descrição!");
            return null;
        }

        if (produto.getPrecoProduto() <= 0) {
            System.err.println("Erro! não é possível cadastrar produto com o preço menor ou igual a zero!");
            return null;
        }

        if (produto.getEstoqueProduto() < 0) {
            System.err.println("Erro! não é possível cadastrar produto com estoque menor que zero!");
            return null;
        }

        return produtoRepository.cadastrarProduto(produto);

    }

    public Produto removerProduto(long codigoDoProduto) {
        Produto produto = consultarProdutoPeloId(codigoDoProduto);
        if (produto == null) {
            System.err.println("Código de produto não encontrado!");
            return null;
        }
        return produtoRepository.removerProduto(produto);

    }

    public Produto consultarProdutoPeloId(long codigoProduto) {
        for (Produto p : produtoRepository.todosProdutos()) {
            if (p.getCodigoProduto() == codigoProduto) {
                return p;
            }
        }
        return null;
    }

    public void atualizarProdutoEstoque(Produto p, int quantidadeProdutoVendido) {
        p.setEstoqueProduto(p.getEstoqueProduto() + quantidadeProdutoVendido);
        produtoRepository.atualizarBaseDados();
    }

    public void definirProdutoEstoque(Produto p, int quantidadeNovoEstoque) {
        p.setEstoqueProduto(quantidadeNovoEstoque);
        produtoRepository.atualizarBaseDados();
    }

    public void listarTodosProdutos() {

        String cabecalho = formatarColuna("ID", 3) +
                formatarColuna("PRODUTO", 41) +
                formatarColuna("ESTOQUE", 10) +
                formatarColuna("PREÇO UNIDADE", 20) +
                formatarColuna("DESCRIÇÃO DO PRODUTO", 44);

        System.out.println(cabecalho);

        for (Produto dados : produtoRepository.todosProdutos()) {
            System.out.println(String.format("%s%s%sR$ %s%s",
                    formatarColuna(String.valueOf(dados.getCodigoProduto()), 3),
                    formatarColuna(dados.getNomeProduto(), 41),
                    formatarColuna(String.valueOf(dados.getEstoqueProduto()), 10),
                    formatarColuna(String.valueOf(dados.getPrecoProduto()), 17),
                    formatarColuna(dados.getDescricaoProduto(), 44)));
        }
    }
}
