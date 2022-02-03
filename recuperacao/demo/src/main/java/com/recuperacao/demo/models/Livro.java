package com.recuperacao.demo.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;


    private String nomeLivro;

    @OneToOne
    @JoinColumn(name = "editora_id")
    Editora editora;

    @OneToOne
    @JoinColumn(name = "autor_id")
    Autor autor;

    @OneToOne
    @JoinColumn(name = "categoria_id")
    Categoria categoria;

    @ManyToMany(mappedBy = "areaConhecimentosLivros")
    private List<AreaConhecimento> areasConhecimento;

    public Livro(){

    }

    public Livro(Long idLivro, String nomeLivro, Editora editora, Autor autor, Categoria categoria, List<AreaConhecimento> areasConhecimento) {

        this.idLivro = idLivro;
        this.nomeLivro = nomeLivro;
        this.editora = editora;
        this.autor = autor;
        this.categoria = categoria;
        this.areasConhecimento = areasConhecimento;

    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<AreaConhecimento> getAreasConhecimento() {
        return areasConhecimento;
    }

    public void setAreasConhecimento(List<AreaConhecimento> areasConhecimento) {
        this.areasConhecimento = areasConhecimento;
    }
}