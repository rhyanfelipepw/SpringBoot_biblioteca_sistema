package com.recuperacao.demo.service;


import com.recuperacao.demo.models.*;
import com.recuperacao.demo.repository.RepositoryEditora;
import com.recuperacao.demo.repository.RepositoryEmprestimo;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class EmprestimoService {
    @Autowired
    private RepositoryEmprestimo repository;
    @Autowired
    private PessoaService servicePessoa;
    @Autowired
    private LivroService livroService;
    @Autowired
    private ExemplarService exemplarService;
    @Autowired
    private CategoriaService CategoriaService;


    public Emprestimo criarEmprestimo(Emprestimo emprestimo) throws Exception {
        if (emprestimo.getPessoa() != null) {
            Pessoa pessoa = emprestimo.getPessoa();
            List<Emprestimo> empExist = repository.findByDevolvidoAndPessoa_idPessoa(false, pessoa.getIdPessoa());
            if (empExist.stream().count() < 1) {
                List<Exemplar> ex = exemplarService.verificaExemplaresDisponiveis(emprestimo.getExemplar());
                if (ex.stream().count() > 0) {
                    Exemplar exemp =  ex.get(0);
                    exemp.setDisponivel(false);
                    emprestimo.setExemplar(exemp);
                    exemplarService.editarExemplar(exemp);
                    Optional<Categoria> cat = CategoriaService.buscarCategoriaPorId(emprestimo.getExemplar().getLivro().getCategoria().getIdCategoria());
                    emprestimo.calculaDiaDevolucao(cat.get().getPrazoCat());
                    return repository.save(emprestimo);
                } else {
                    throw new Exception("Exemplar não disponível");
                }
            } else
                throw new Exception("Usuário está com um livro");
        } else {
            throw new Exception("Usuário não foi informado");
        }
    }
    public Emprestimo devolverEmprestimo(long idEmprestimo){
        Optional<Emprestimo> emp = repository.findById(idEmprestimo);
        emp.get().setDevolvido(true);
        return repository.save(emp.get());
    }


    public Emprestimo editarEmprestimo(Emprestimo emprestimo) {
        return repository.save(emprestimo);
    }

    public Collection<Emprestimo> getAtrasados() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        return repository.FindAtrasados(date);
    }

    public List<Emprestimo> buscarTodosEmprestimosNaoDevolvidos(){
        return repository.findByDevolvido(false);
    }


    public void deletarEmprestimo(Emprestimo emprestimo) {
        repository.delete(emprestimo);
    }

    public List<Emprestimo> buscarTodosEmprestimos() {
        return repository.findAll();
    }

    public Optional<Emprestimo> buscarEmprestimoPorId(Long id) {
        return repository.findById(id);
    }

}
