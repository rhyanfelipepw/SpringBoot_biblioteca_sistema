package com.recuperacao.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static org.springframework.util.Assert.notNull;

@Entity
public class Emprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmprestimo")
    private Long idEmprestimo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dataEmprestimo")
    private Date dataEmprestimo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "dataDevolucao")
    private Date dataDevolucao;

    @Column(name = "devolvido")
    private boolean devolvido;

    @OneToOne
    @JoinColumn(name="usuario_id")
    private Pessoa pessoa;

    @OneToOne
    @JoinColumn(name="exemplar_id")
    private Exemplar exemplar;

    public Emprestimo() {

    }

    public Emprestimo(Long idEmprestimo, Date dataEmprestimo, Date dataDevolucao, boolean devolvido, Pessoa pessoa, Exemplar exemplar) {
        this.idEmprestimo = idEmprestimo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
        this.pessoa = pessoa;
        this.exemplar = exemplar;
    }

    public void calculaDiaDevolucao(int dias){
        Calendar cal = Calendar.getInstance();
        Date dataEmprestimo =cal.getTime();
        this.dataEmprestimo = dataEmprestimo;
        cal.add(Calendar.DATE, dias);
        Date dataDevolucao = cal.getTime();
        this.dataDevolucao = dataDevolucao;
    }


    public Long getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Long idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }
}