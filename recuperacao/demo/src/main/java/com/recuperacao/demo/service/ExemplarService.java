package com.recuperacao.demo.service;


import com.recuperacao.demo.models.Emprestimo;
import com.recuperacao.demo.models.Exemplar;
import com.recuperacao.demo.repository.RepositoryEmprestimo;
import com.recuperacao.demo.repository.RepositoryExemplar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExemplarService {
    @Autowired
    private RepositoryExemplar repository;


    public ResponseEntity criarExemplar(Exemplar exemplar) {
        Exemplar response = repository.save(exemplar);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity editarExemplar(Exemplar exemplar) {
        Exemplar response = repository.save(exemplar);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity deletarExemplar(Exemplar ex) {
        repository.delete(ex);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    public ResponseEntity buscarTodosExemplares() {
        List<Exemplar> response = repository.findAll();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity buscarExemplarPorId(Long id) {
        Optional<Exemplar> response = repository.findById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    public  List<Exemplar> verificaExemplaresDisponiveis(Exemplar ex){
       return repository.findByDisponivelAndLivro_idLivro(true, ex.getLivro().getIdLivro());
    }
}
