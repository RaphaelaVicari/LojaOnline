package org.example.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Cliente;
import org.example.util.RepositoryUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClienteRepository {

    private static final String CLIENTES_JSON = "./clientes.json";
    private List<Cliente> clienteList;

    private RepositoryUtil utilidades;

    private ObjectMapper mapeador;

    public ClienteRepository() {
        utilidades = new RepositoryUtil();
        mapeador = new ObjectMapper();
        try {
            byte[] dados = utilidades.lerArquivo(CLIENTES_JSON);
            clienteList = mapeador.readValue(dados, new TypeReference<>() {
            });
        } catch (IOException e) {
            clienteList = new ArrayList<>();
        }
    }

    public Cliente cadastrarCliente(Cliente registroNovoCliente) {

        clienteList.add(registroNovoCliente);
        try {
            String saida = mapeador.writeValueAsString(clienteList);
            utilidades.persistirArquivo(CLIENTES_JSON, saida);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return registroNovoCliente;
    }

    public Cliente consultarCliente(String cpfCliente){

        for(Cliente dados : clienteList){
            if (dados.getCpfCliente().equalsIgnoreCase(cpfCliente)){
                return dados;
            }
        }
        return null;
    }
}
