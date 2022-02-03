package com.recuperacao.demo.service;


import com.recuperacao.demo.models.Categoria;
import com.recuperacao.demo.models.Editora;
import com.recuperacao.demo.repository.RepositoryCategoria;
import com.recuperacao.demo.repository.RepositoryEditora;
import com.recuperacao.demo.repository.RepositoryLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {
    @Autowired
    private RepositoryEditora repository;
    @Autowired
    private RepositoryLivro livroRepository;


    public ResponseEntity criarEditora(Editora editora) {
        Editora response = repository.save(editora);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity editarEditora(Editora editora) {
        Editora response = repository.save(editora);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity deletarEditora(Editora editora) {
        repository.delete(editora);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    public ResponseEntity buscarTodasEditoras() {
         List<Editora> response =  repository.findAll();
         for(Editora ed: response){
             ed.setQuantidadeLivros((int) livroRepository.findByEditora_idEditora(ed.getIdEditora()).stream().count());
         }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity buscarEditoraPorId(Long id) {
        Optional<Editora> response =  repository.findById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }



}
