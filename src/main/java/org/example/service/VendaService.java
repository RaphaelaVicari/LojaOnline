package org.example.service;

import org.example.model.Venda;
import org.example.repository.VendaRepository;

public class VendaService {

    private ClienteService clienteService;

    private VendaRepository vendaRepository;

    public VendaService(ClienteService clienteService) {
        this.clienteService = clienteService;
        this.vendaRepository = new VendaRepository();
    }

    public Venda cadastrarVenda(Venda venda) {
        if (venda.getListaProdutos().isEmpty()) {
            System.err.println("Não é possivel realizar uma compra sem produtos");
            return null;
        }

        if (venda.getClienteVenda() == null) {
            System.err.println("Não é possivel realizar uma compra sem ter cadastro de cliente!");
            return null;
        }

        if (venda.getClienteVenda().getSaldo() < venda.getValorTotalVenda()) {
            System.err.println("Erro, Saldo insuficiente!");
            System.err.println("Compre GiftCard para finalizar a compra");
            return null;
        }

        Venda vendaRealizada = vendaRepository.cadastrarVenda(venda);
        if (vendaRealizada == null) {
            System.err.println("Erro ao finalizar compra");
            return null;
        }

        venda.getClienteVenda().setSaldo(venda.getClienteVenda().getSaldo() - venda.getValorTotalVenda());
        clienteService.atualizarClienteSaldo(venda.getClienteVenda(), venda.getClienteVenda().getSaldo());
        return vendaRealizada;
    }

}
