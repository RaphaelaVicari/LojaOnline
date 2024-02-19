package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Cliente;
import org.example.model.Produto;
import org.example.util.RepositoryUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    private static final String PRODUTOS_JSON = "./produtos.json";
    private List<Produto> produtoList;

    private RepositoryUtil utilidades;

    private ObjectMapper mapeador;

    public ProdutoRepository() {
        utilidades = new RepositoryUtil();
        mapeador = new ObjectMapper();
        try {
            byte[] dados = utilidades.lerArquivo(PRODUTOS_JSON);
            produtoList = mapeador.readValue(dados, new TypeReference<>() {
            });
        } catch (IOException e) {
            produtoList = new ArrayList<>();
        }
    }

    public Produto cadastrarProduto(Produto registroNovoProduto) {
        produtoList.add(registroNovoProduto);
        try {
            String saida = mapeador.writeValueAsString(produtoList);
            utilidades.persistirArquivo(PRODUTOS_JSON, saida);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return registroNovoProduto;
    }

    public List<Produto> consultarProduto(String nomeProduto){
        List<Produto> produtosEncontrados = new ArrayList<>();
        for(Produto dados : produtoList){
            if (dados.getNomeProduto().toLowerCase().startsWith(nomeProduto.toLowerCase())){
                produtosEncontrados.add(dados);
            }
        }
        return produtosEncontrados;
    }

    public List<Produto> todosProdutos(){
        return produtoList;
    }
}
