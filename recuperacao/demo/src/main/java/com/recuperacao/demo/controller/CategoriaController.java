package com.recuperacao.demo.controller;


import com.recuperacao.demo.models.Categoria;
import com.recuperacao.demo.models.Emprestimo;
import com.recuperacao.demo.service.CategoriaService;
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
@RequestMapping({"/categoria"})
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @PostMapping(path = "/criar")
    public ResponseEntity criaUsuario(@RequestBody Categoria categoria) {
        return service.criarCategoria(categoria);
    }

    @GetMapping(path = "/getTodos")
    public ResponseEntity buscarTodasCategorias() {
        return service.buscarTodasCategorias();
    }

    @GetMapping(path = "/getPorId")
    public ResponseEntity buscarCategoriaPorId(@RequestParam long idCategoria) {

        try {
            Optional<Categoria> response =  service.buscarCategoriaPorId(idCategoria);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<String> editarCategoria(@RequestBody Categoria categoria) {
        return service.editarCategoria(categoria);

    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity deletarCategoria(@RequestBody Categoria categoria) {
        return service.deletarCategoria(categoria);
    }


}
