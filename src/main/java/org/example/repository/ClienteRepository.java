package org.example.repository;

import org.example.service.ClienteService;
import org.example.service.ClienteService.CPF;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Cliente;
import org.example.util.RepositoryUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ClienteRepository {

    private static final String CLIENTES_JSON = "./clientes.json";
    private List<Cliente> clienteList;

    private RepositoryUtil utilidades;

    private ObjectMapper mapeador;
    
    private ClienteService clienteService;
    
 // Adicionar o método para validar CPF
    private boolean validarCPF(String cpf) {
        CPF cpfUtil = new CPF(cpf);
        
        if (!cpfUtil.isCPF()) {        	
            return false;
        }

        return true;
    }
    
    private boolean validarEmail(String email) {

        // Chama o método validarEmail da classe ClienteService
        return clienteService.validarEmail(email);
    }
    
    private boolean cpfJaCadastrado(String cpf) {
        // Verificar se o CPF já está cadastrado na lista de clientes
        for (Cliente dados : clienteList) {
            if (dados.getCpfCliente().equalsIgnoreCase(cpf)) {
                return true;
            }
        }
        return false;
    }

    public ClienteRepository() {
        utilidades = new RepositoryUtil();
        mapeador = new ObjectMapper();
        clienteList = new ArrayList<>();
        clienteService = new ClienteService();
        
        try {
            byte[] dados = utilidades.lerArquivo(CLIENTES_JSON);
            clienteList = mapeador.readValue(dados, new TypeReference<>() {
            });
        } catch (IOException e) {
            clienteList = new ArrayList<>();
        }
    }

    public Cliente cadastrarCliente(Cliente registroNovoCliente) {
    	
    	 if (!validarCPF(registroNovoCliente.getCpfCliente())) {
    		 //System.out.println("CPF inválido. Não foi possível cadastrar o cliente.");
             JOptionPane.showMessageDialog(null, "CPF inválido. Não foi possível cadastrar o cliente.");
             return null;
         }
    	 
    	 if (!validarEmail(registroNovoCliente.getEmailCliente())) {
             JOptionPane.showMessageDialog(null, "E-mail inválido. Não foi possível cadastrar o cliente.");
             return null;
         }
    	 
    	 if (cpfJaCadastrado(registroNovoCliente.getCpfCliente())) {
    		 //System.out.println("CPF já cadastrado. Não é possível cadastrar novamente.");
             JOptionPane.showMessageDialog(null, "CPF já cadastrado. Não é possível cadastrar novamente.");
             return null;
         }
        try {
        	clienteList.add(registroNovoCliente);
            String saida = mapeador.writeValueAsString(clienteList);
            utilidades.persistirArquivo(CLIENTES_JSON, saida);                      
           
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return registroNovoCliente;
    }
    

    public Cliente consultarClientePorCpf(String cpfCliente){

        for(Cliente dados : clienteList){
            if (dados.getCpfCliente().equalsIgnoreCase(cpfCliente)){
                return dados;
            }
        }
        return null;
    }
}
