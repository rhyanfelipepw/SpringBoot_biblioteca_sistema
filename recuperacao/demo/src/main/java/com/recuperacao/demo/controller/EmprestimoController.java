package com.recuperacao.demo.controller;


import com.recuperacao.demo.models.Emprestimo;
import com.recuperacao.demo.service.EmprestimoService;
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
@RequestMapping({"/emprestimo"})
public class EmprestimoController {
    @Autowired
    private EmprestimoService service;

    @PostMapping(path = "/criar")
    public ResponseEntity criarEmprestimo(@RequestBody Emprestimo emprestimo) {
        try {
            Emprestimo response = service.criarEmprestimo(emprestimo);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(path = "/devolver")
    public ResponseEntity devolverEmprestimo(@RequestParam int idEmprestimo) {
        try {
            Emprestimo response = service.devolverEmprestimo(idEmprestimo);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/buscarTodosNaoDevolvidos")
    public ResponseEntity buscarTodosEmprestimos() {
        try {
            List<Emprestimo> response = service.buscarTodosEmprestimosNaoDevolvidos();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path="/buscarTodos")
    public ResponseEntity buscarTodos(){
        try {
            List<Emprestimo> response = service.buscarTodosEmprestimos();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(path = "/getAtrasados")
    public ResponseEntity getAtrasados() {
        try {
            Collection<Emprestimo> response = service.getAtrasados();
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/getPorId")
    public ResponseEntity buscarEmprestimoPorId(@RequestParam long idEmprestimo) {
        try {
            Optional<Emprestimo> response = service.buscarEmprestimoPorId(idEmprestimo);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/edit")
    public ResponseEntity editarEmprestimo(@RequestBody Emprestimo emprestimo) {
        try {
            Emprestimo response = service.editarEmprestimo(emprestimo);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity deletarEmprestimo(@RequestBody Emprestimo emprestimo) {
        try {
            service.deletarEmprestimo(emprestimo);
            return new ResponseEntity("OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }


}
