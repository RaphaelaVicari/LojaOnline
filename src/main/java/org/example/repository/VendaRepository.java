package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Produto;
import org.example.model.Venda;
import org.example.util.RepositoryUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VendaRepository {

    private static final String VENDAS_JSON = "./vendas.json";
    private List<Venda> vendaList;

    private RepositoryUtil utilidades;

    private ObjectMapper mapeador;

    public VendaRepository() {
        utilidades = new RepositoryUtil();
        mapeador = new ObjectMapper();
        try {
            byte[] dados = utilidades.lerArquivo(VENDAS_JSON);
            vendaList = mapeador.readValue(dados, new TypeReference<>() {
            });
        } catch (IOException e) {
            vendaList = new ArrayList<>();
        }
    }

    public Venda cadastrarVenda(Venda registroNovaVenda) {
        vendaList.add(registroNovaVenda);
        try {
            String saida = mapeador.writeValueAsString(vendaList);
            utilidades.persistirArquivo(VENDAS_JSON, saida);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return registroNovaVenda;
    }

    public List<Venda> todasVendas(){
        return vendaList;
    }
}
