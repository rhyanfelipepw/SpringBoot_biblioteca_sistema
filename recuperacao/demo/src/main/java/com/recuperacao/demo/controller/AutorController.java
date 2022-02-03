package com.recuperacao.demo.controller;


import com.recuperacao.demo.models.Autor;
import com.recuperacao.demo.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(
        origins = {"*"},
        maxAge = 3600,
        allowCredentials = "false")
@RestController
@RequestMapping({"/autor"})
public class AutorController {
    @Autowired
    private AutorService service;

    @PostMapping(path = "/criar")
    public ResponseEntity criarAutor(@RequestBody Autor autor) {
        return service.criarAutor(autor);
    }

    @GetMapping(path = "/getTodos")
    public ResponseEntity getTodosAutores() {
        return service.buscarTodosAutores();
    }

    @GetMapping(path = "/getPorId")
    public ResponseEntity getAutorPorId(@RequestParam long idAutor) {
        return service.buscarAutorPorId(idAutor);
    }

    @PutMapping(path = "/edit")
    public ResponseEntity editContato(@RequestBody Autor autor) {
        return service.editarAutor(autor);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity deleteContato(@RequestBody Autor autor) {
        return service.deletarAutor(autor);

    }
}
