package com.recuperacao.demo.controller;


import com.recuperacao.demo.models.Exemplar;
import com.recuperacao.demo.service.ExemplarService;
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
@RequestMapping({"/exemplar"})
public class ExemplarController {
    @Autowired
    private ExemplarService service;

    @PostMapping(path = "/criar")
    public ResponseEntity criarExemplar(@RequestBody Exemplar exemplar) {
        return service.criarExemplar(exemplar);
    }

    @GetMapping(path = "/buscarTodos")
    public ResponseEntity buscarTodosExemplares() {
        return service.buscarTodosExemplares();
    }

    @GetMapping(path = "/getPorId")
    public ResponseEntity buscarExemplarPorId(@RequestParam long idExemplar) {
        return service.buscarExemplarPorId(idExemplar);
    }

    @PutMapping(path = "/edit")
    public ResponseEntity editarExemplar(@RequestBody Exemplar exemplar) {
        return service.editarExemplar(exemplar);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity deletarExemplar(@RequestBody  Exemplar exemplar) {
        return service.deletarExemplar(exemplar);
    }
    @GetMapping(path = "/buscarTodosLivrosExemplar")
    public ResponseEntity buscarTodosLivrosExemplar() {
        return service.buscarTodosExemplares();
    }

}
