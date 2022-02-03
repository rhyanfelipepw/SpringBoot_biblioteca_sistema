package com.recuperacao.demo.service;
import aj.org.objectweb.asm.Type;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recuperacao.demo.models.EnderecoAPI;
import com.recuperacao.demo.models.Pessoa;
import com.recuperacao.demo.repository.RepositoryPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private RepositoryPessoa repository;


    public Pessoa criarPessoa(Pessoa pessoa) throws Exception {
        // TESTA CEP RESIDENCIAL
        Map residencial = criarEndereco(pessoa.getEnderecoRes_refEndereco());
        Map comercial = criarEndereco(pessoa.getEnderecoCom_refEndereco());
        if(residencial.get("status").equals("200")){
            if(comercial.get("status").equals("200"))
            {
                return repository.save(pessoa);
            }else{
                throw new Exception("O CEP COMERCIAL É INVALIDO");
            }
        }else{
            throw new Exception("O CEP RESIDENCIAL É INVALIDO");
        }
    }

    public Pessoa editarPessoa(Pessoa pessoa) {
     return repository.save(pessoa);
    }

    public void deletarPessoa(Pessoa pessoa) {
        repository.delete(pessoa);
    }

    public List<Pessoa> buscarTodasPessoas() {
        return repository.findAll();
    }

    public Optional<Pessoa> buscarPessoaPorId(Long id) {
        return repository.findById(id);
    }


    public Map criarEndereco(int CEP) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();

        String uri = String.format("https://ws.apicep.com/cep/%d.json", CEP); // or any other uri

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

         ResponseEntity<String>  teste = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
         Map api = mapper.readValue(teste.getBody(), new TypeReference<Map<String, String>>(){});
         return api;
    }


}
