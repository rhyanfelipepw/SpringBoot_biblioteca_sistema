package com.recuperacao.demo.service;

import com.recuperacao.demo.models.AreaConhecimento;
import com.recuperacao.demo.repository.RepositoryAreaConhecimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AreaConhecimentoService {
    @Autowired
    private RepositoryAreaConhecimento repository;


    public ResponseEntity criarAreaConhecimento(AreaConhecimento areaConhecimento) {
        AreaConhecimento response = repository.save(areaConhecimento);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity editarAreaConhecimento(AreaConhecimento areaConhecimento) {
        AreaConhecimento response = repository.save(areaConhecimento);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity deletaAreaConhecimento(AreaConhecimento areaConhecimento) {
        repository.delete(areaConhecimento);
        return new ResponseEntity("OK",HttpStatus.OK);
    }

    public ResponseEntity<List<AreaConhecimento>> buscarTodasAreaConhecimento() {
        List<AreaConhecimento> response = repository.findAll();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public AreaConhecimento buscarAreaConhecimentoPorId(Long id) {
       Optional<AreaConhecimento> area = repository.findById(id);
       return area.get();
    }

}
