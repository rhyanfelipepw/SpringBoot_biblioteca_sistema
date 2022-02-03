package com.recuperacao.demo.models;

import javax.persistence.*;

@Entity
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExemplar;

    private boolean disponivel;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    public Exemplar() {
    }

    public Exemplar(Long idExemplar,boolean disponivel, Livro livro) {
        this.idExemplar = idExemplar;
        this.livro = livro;
        this.disponivel = disponivel;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Long getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(Long idExemplar) {
        this.idExemplar = idExemplar;
    }


    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}