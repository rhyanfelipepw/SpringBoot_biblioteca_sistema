package com.recuperacao.demo.service;


import com.recuperacao.demo.models.Autor;
import com.recuperacao.demo.models.Categoria;
import com.recuperacao.demo.repository.RepositoryAutor;
import com.recuperacao.demo.repository.RepositoryCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private RepositoryCategoria repository;


    public ResponseEntity criarCategoria(Categoria categoria){
        Categoria response = repository.save(categoria);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity editarCategoria(Categoria categoria){
        Categoria response = repository.save(categoria);
       return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity deletarCategoria(Categoria categoria){
        repository.delete(categoria);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    public ResponseEntity buscarTodasCategorias(){
        List<Categoria> response = repository.findAll();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public Optional<Categoria> buscarCategoriaPorId(Long id){
        return  repository.findById(id);
    }

}
