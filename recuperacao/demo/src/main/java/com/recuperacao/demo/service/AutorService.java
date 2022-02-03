package com.recuperacao.demo.service;


import com.recuperacao.demo.models.Autor;
import com.recuperacao.demo.repository.RepositoryAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    private RepositoryAutor repository;


    public ResponseEntity criarAutor(Autor autor) {
        Autor response = repository.save(autor);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity editarAutor(Autor autor) {
        Autor response = repository.save(autor);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity deletarAutor(Autor autor) {
        repository.delete(autor);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    public ResponseEntity buscarTodosAutores() {
        List<Autor> autores = repository.findAll();
        return new ResponseEntity(autores, HttpStatus.OK);
    }

    public ResponseEntity buscarAutorPorId(Long id) {
        Optional<Autor> autor = repository.findById(id);
        return new ResponseEntity(autor, HttpStatus.OK);
    }

}
