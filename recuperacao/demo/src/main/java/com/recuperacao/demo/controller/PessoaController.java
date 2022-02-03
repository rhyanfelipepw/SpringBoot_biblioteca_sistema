package com.recuperacao.demo.controller;


import com.recuperacao.demo.models.Pessoa;
import com.recuperacao.demo.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(
        origins = {"*"},
        maxAge = 3600,
        allowCredentials = "false")
@RestController
@RequestMapping({"/pessoa"})
public class PessoaController {
    @Autowired
    private PessoaService service;

    @PostMapping(path = "/criar")
    public ResponseEntity criarPessoa(@RequestBody Pessoa pessoa) {
        try {
            Pessoa response = service.criarPessoa(pessoa);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/buscarTodas")
    public ResponseEntity buscarTodasPessoas() {
        try {
            List<Pessoa> response = service.buscarTodasPessoas();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/getPorId")
    public ResponseEntity buscarPessoaPorId(@RequestParam long idPessoa) {
        try {
            Optional<Pessoa> response = service.buscarPessoaPorId(idPessoa);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/edit")
    public ResponseEntity editarPessoa(@RequestBody Pessoa pessoa) {
        try {
            Pessoa response  = service.editarPessoa(pessoa);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity deletarPessoa(@RequestBody Pessoa pessoa) {
        try {
            service.deletarPessoa(pessoa);;
            return new ResponseEntity("OK", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.OK);
        }
    }


}
