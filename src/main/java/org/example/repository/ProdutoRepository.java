package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Produto;
import org.example.util.RepositoryUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProdutoRepository {

    private static final String PRODUTOS_JSON = "./produtos.json";
    private List<Produto> produtoList;

    private RepositoryUtil utilidades;

    private ObjectMapper mapeador;

    public ProdutoRepository() {
        utilidades = new RepositoryUtil();
        mapeador = new ObjectMapper();
        lerBaseDados();
    }

    public void lerBaseDados() {
        try {
            byte[] dados = utilidades.lerArquivo(PRODUTOS_JSON);
            produtoList = mapeador.readValue(dados, new TypeReference<>() {
            });
            produtoList.sort(Comparator.comparingLong(Produto::getCodigoProduto));
        } catch (IOException e) {
            produtoList = new ArrayList<>();
        }
    }

    public Produto cadastrarProduto(Produto registroNovoProduto) {
        produtoList.add(registroNovoProduto);
        if (atualizarBaseDados()){
            return null;
        }
        return registroNovoProduto;
    }

    public boolean atualizarBaseDados() {
        try {
            String saida = mapeador.writerWithDefaultPrettyPrinter().writeValueAsString(produtoList);
            utilidades.persistirArquivo(PRODUTOS_JSON, saida);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        produtoList.sort(Comparator.comparingLong(Produto::getCodigoProduto));
        return true;
    }

    public List<Produto> todosProdutos() {
        return produtoList;
    }

    public Produto removerProduto(Produto produto) {
        produtoList.remove(produto);
        atualizarBaseDados();
        return produto;
    }
}
