package org.example.model;

import java.sql.Date;

public class Cliente {

    private String cpfCliente;

    private String nomeCliente;

    private String emailCliente;

    private String numeroCelularCliente;

    private String dataNascimentoCliente;

    private String senhaCliente;

    private String enderecoCliente;

    private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getNumeroCelularCliente() {
        return numeroCelularCliente;
    }

    public void setNumeroCelularCliente(String numeroCelularCliente) {
        this.numeroCelularCliente = numeroCelularCliente;
    }

    public String getDataNascimentoCliente() {
        return dataNascimentoCliente;
    }

    public void setDataNascimentoCliente(String data) {
        this.dataNascimentoCliente = data;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpfCliente='" + cpfCliente + '\'' +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", emailCliente='" + emailCliente + '\'' +
                ", numeroCelularCliente='" + numeroCelularCliente + '\'' +
                ", dataNascimentoCliente='" + dataNascimentoCliente + '\'' +
                ", senhaCliente='" + senhaCliente + '\'' +
                ", enderecoCliente='" + enderecoCliente + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
