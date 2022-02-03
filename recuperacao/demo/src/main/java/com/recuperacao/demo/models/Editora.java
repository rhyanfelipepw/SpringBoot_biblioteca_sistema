package com.recuperacao.demo.models;

import com.recuperacao.demo.repository.RepositoryLivro;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEditora;
    private String nome;
    private int quantidadeLivros;

    public Editora() {
    }

    public Editora(Long idEditora, String nome, int quantidadeLivros) {
        this.idEditora = idEditora;
        this.nome = nome;
        this.quantidadeLivros = quantidadeLivros;
    }

    public int getQuantidadeLivros() {
        return quantidadeLivros;
    }

    public void setQuantidadeLivros(int quantidadeLivros) {
        this.quantidadeLivros = quantidadeLivros;
    }



    public Long getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(Long idEditora) {
        this.idEditora = idEditora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}