package com.recuperacao.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class AreaConhecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAreaConhecimento;


    private String nomeAreaConhecimento;

    @ManyToMany
    @JoinTable(name = "livros_areasConhecimentos", joinColumns = @JoinColumn(name = "areaConhecimento_id"), inverseJoinColumns = @JoinColumn(name = "livro_id"))
    private List<Livro> areaConhecimentosLivros;

    public AreaConhecimento() {
    }

    public AreaConhecimento(Long idAreaConhecimento, String nomeAreaConhecimento, List<Livro> areaConhecimentosLivros) {
        this.idAreaConhecimento = idAreaConhecimento;
        this.nomeAreaConhecimento = nomeAreaConhecimento;
        this.areaConhecimentosLivros = areaConhecimentosLivros;
    }

    public Long getIdAreaConhecimento() {
        return idAreaConhecimento;
    }

    public void setIdAreaConhecimento(Long idAreaConhecimento) {
        this.idAreaConhecimento = idAreaConhecimento;
    }

    public String getNomeAreaConhecimento() {
        return nomeAreaConhecimento;
    }

    public void setNomeAreaConhecimento(String nomeAreaConhecimento) {
        this.nomeAreaConhecimento = nomeAreaConhecimento;
    }

    public List<Livro> getAreaConhecimentosLivros() {
        return areaConhecimentosLivros;
    }

    public void setAreaConhecimentosLivros(List<Livro> areaConhecimentosLivros) {
        this.areaConhecimentosLivros = areaConhecimentosLivros;
    }
}