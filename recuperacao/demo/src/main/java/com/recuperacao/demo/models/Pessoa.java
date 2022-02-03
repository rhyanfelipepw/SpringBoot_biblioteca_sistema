package com.recuperacao.demo.models;

import javax.persistence.*;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;
    private String nome;
    private String rg;
    private String cpf;
    private int enderecoCom_refEndereco;
    private int enderecoCom_numero;
    private String endereoCom_complem;
    private int enderecoRes_refEndereco;
    private int enderecoRes_numero;
    private String endereoRes_complem;
    private String email;
    private String telefone;

    public Pessoa() {

    }

    public Pessoa(Long idPessoa, String nome, String rg, String cpf, int enderecoCom_refEndereco, int enderecoCom_numero, String endereoCom_complem, int enderecoRes_refEndereco, int enderecoRes_numero, String endereoRes_complem, String email, String telefone) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.enderecoCom_refEndereco = enderecoCom_refEndereco;
        this.enderecoCom_numero = enderecoCom_numero;
        this.endereoCom_complem = endereoCom_complem;
        this.enderecoRes_refEndereco = enderecoRes_refEndereco;
        this.enderecoRes_numero = enderecoRes_numero;
        this.endereoRes_complem = endereoRes_complem;
        this.email = email;
        this.telefone = telefone;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getEnderecoCom_refEndereco() {
        return enderecoCom_refEndereco;
    }

    public void setEnderecoCom_refEndereco(int enderecoCom_refEndereco) {
        this.enderecoCom_refEndereco = enderecoCom_refEndereco;
    }

    public int getEnderecoCom_numero() {
        return enderecoCom_numero;
    }

    public void setEnderecoCom_numero(int enderecoCom_numero) {
        this.enderecoCom_numero = enderecoCom_numero;
    }

    public String getEndereoCom_complem() {
        return endereoCom_complem;
    }

    public void setEndereoCom_complem(String endereoCom_complem) {
        this.endereoCom_complem = endereoCom_complem;
    }

    public int getEnderecoRes_refEndereco() {
        return enderecoRes_refEndereco;
    }

    public void setEnderecoRes_refEndereco(int enderecoRes_refEndereco) {
        this.enderecoRes_refEndereco = enderecoRes_refEndereco;
    }

    public int getEnderecoRes_numero() {
        return enderecoRes_numero;
    }

    public void setEnderecoRes_numero(int enderecoRes_numero) {
        this.enderecoRes_numero = enderecoRes_numero;
    }

    public String getEndereoRes_complem() {
        return endereoRes_complem;
    }

    public void setEndereoRes_complem(String endereoRes_complem) {
        this.endereoRes_complem = endereoRes_complem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}