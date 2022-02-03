package com.recuperacao.demo.controller;


import com.recuperacao.demo.models.Editora;
import com.recuperacao.demo.service.EditoraService;
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
@RequestMapping({"/editora"})
public class EditoraController {
    @Autowired
    private EditoraService service;

    @PostMapping(path = "/criar")
    public ResponseEntity criarEditora(@RequestBody Editora editora) {
        return service.criarEditora(editora);
    }

    @GetMapping(path = "/buscarTodos")
    public ResponseEntity buscarTodasEditoras() {
        return service.buscarTodasEditoras();
    }

    @GetMapping(path = "/getPorId")
    public ResponseEntity buscarEditoraPorId(@RequestParam long idEditora) {
        return service.buscarEditoraPorId(idEditora);
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<String> editarEditora(@RequestBody Editora editora) {
        return service.editarEditora(editora);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity deletarEditora(@RequestBody Editora editora) {
        return service.deletarEditora(editora);
    }


}
