package com.recuperacao.demo.controller;


import com.recuperacao.demo.models.AreaConhecimento;
import com.recuperacao.demo.models.Emprestimo;
import com.recuperacao.demo.service.AreaConhecimentoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.Area;
import java.util.List;
import java.util.Optional;

@CrossOrigin(
        origins = {"*"},
        maxAge = 3600,
        allowCredentials = "false")
@RestController
@RequestMapping({"/areaConhecimento"})
public class AreaConhecimentoController {
    @Autowired
    private AreaConhecimentoService service;

    @PostMapping(path = "/criar")
    public ResponseEntity criarAreaConhecimento(@RequestBody AreaConhecimento area) {
        return service.criarAreaConhecimento(area);
    }

    @GetMapping(path = "/getTodos")
    public ResponseEntity getAreaConhecimento() {
        return service.buscarTodasAreaConhecimento();
    }

    @GetMapping(path = "/getPorId")
    public ResponseEntity getAreaConhecimentoId(@RequestParam long idAreaConhecimento) {
        try {
            AreaConhecimento response = service.buscarAreaConhecimentoPorId(idAreaConhecimento);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/edit")
    public ResponseEntity editAreaConhecimento(@RequestBody AreaConhecimento area) {
        return service.editarAreaConhecimento(area);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity deleteAreaConhecimento(@RequestBody AreaConhecimento area) {
        return service.deletaAreaConhecimento(area);
    }


}
