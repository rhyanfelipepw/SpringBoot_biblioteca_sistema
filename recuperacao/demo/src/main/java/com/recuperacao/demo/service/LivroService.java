package com.recuperacao.demo.service;


import com.recuperacao.demo.models.Exemplar;
import com.recuperacao.demo.models.Livro;
import com.recuperacao.demo.repository.RepositoryExemplar;
import com.recuperacao.demo.repository.RepositoryLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private RepositoryLivro repository;


    public ResponseEntity criarLivro(Livro livro){
        Livro response = repository.save(livro);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity editarLivro(Livro livro){
        Livro response = repository.save(livro);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity deletarLivro(Livro livro){
        repository.delete(livro);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    public ResponseEntity buscarTodosLivros(){
        List<Livro> response = repository.findAll();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity buscarLivroPorId(Long id){
        Optional<Livro> response =  repository.findById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
