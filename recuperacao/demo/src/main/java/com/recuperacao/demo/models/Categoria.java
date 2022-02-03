package com.recuperacao.demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    private String nomeCategoria;
    private int prazoCat;

    public Categoria(Long idCategoria, String nomeCategoria, int prazoCat) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.prazoCat = prazoCat;
    }

    public Categoria() {
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public int getPrazoCat() {
        return prazoCat;
    }

    public void setPrazoCat(int prazoCat) {
        this.prazoCat = prazoCat;
    }

}