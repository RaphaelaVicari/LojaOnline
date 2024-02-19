package org.example.model;

public class VendaProduto {

    public VendaProduto(String nomeProduto, String descricaoProduto, double precoProduto) {
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.precoProduto = precoProduto;
    }

    public VendaProduto() {

    }

    public VendaProduto(Produto produto) {
        setNomeProduto(produto.getNomeProduto());
        setDescricaoProduto(produto.getDescricaoProduto());
        setPrecoProduto(produto.getPrecoProduto());
    }


    private String nomeProduto;

    private String descricaoProduto;

    private double precoProduto;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }
}
