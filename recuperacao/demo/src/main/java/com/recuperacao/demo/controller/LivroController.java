package com.recuperacao.demo.controller;


import com.recuperacao.demo.models.AreaConhecimento;
import com.recuperacao.demo.models.Livro;
import com.recuperacao.demo.service.AreaConhecimentoService;
import com.recuperacao.demo.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        origins = {"*"},
        maxAge = 3600,
        allowCredentials = "false")
@RestController
@RequestMapping({"/livro"})
public class LivroController {
    @Autowired
    private LivroService service;
    @Autowired
    private AreaConhecimentoService areaConhecimentoService;

    @PostMapping(path = "/criar")
    public ResponseEntity criarLivro(@RequestBody Livro livro) {
        return service.criarLivro(livro);
    }

    @GetMapping(path = "/buscarTodos")
    public ResponseEntity buscarTodosLivros() {
        return service.buscarTodosLivros();
    }

    @GetMapping(path = "/getPorId")
    public ResponseEntity buscarLivroPorId(@RequestParam long idLivro) {
        return service.buscarLivroPorId(idLivro);
    }

    @PutMapping(path = "/edit")
    public ResponseEntity editarLivro(@RequestBody Livro livro) {
        return service.editarLivro(livro);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity deletarLivro(@RequestBody Livro livro) {
        return service.deletarLivro(livro);
    }

    @PostMapping(path ="/addLivroArea")
    public ResponseEntity ResponseEntity(@RequestBody Livro livro) {
        for(AreaConhecimento s: livro.getAreasConhecimento()) {
            AreaConhecimento ss = areaConhecimentoService.buscarAreaConhecimentoPorId(s.getIdAreaConhecimento());
            ss.getAreaConhecimentosLivros().add(livro);
            areaConhecimentoService.criarAreaConhecimento(ss);
            return new ResponseEntity("OK", HttpStatus.OK);
        }
        return null;
    }
}
