package org.example.model;

public class VendaProduto {

    public VendaProduto(){

    }

    public VendaProduto(Produto produto) {
        setCodigoProduto(produto.getCodigoProduto());
        setNomeProduto(produto.getNomeProduto());
        setDescricaoProduto(produto.getDescricaoProduto());
        setPrecoProduto(produto.getPrecoProduto());
    }

    private long codigoProduto;

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

    public long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    @Override
    public String toString() {
        return "VendaProduto{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", descricaoProduto='" + descricaoProduto + '\'' +
                ", precoProduto=" + precoProduto +
                '}';
    }
}
