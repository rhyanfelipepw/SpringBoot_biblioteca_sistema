package com.recuperacao.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.recuperacao.demo.models.Pessoa;
import com.recuperacao.demo.service.PessoaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
class PessoaTeste {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PessoaService pessoaService;

    @Test
    void criar() throws Exception {
        Pessoa pessoa = new Pessoa(100l, "Lucas", "", "", 85904000
                ,0,"",85904000
                ,0,"","","");

        String json = objectMapper.writeValueAsString(pessoa);
        RequestBuilder request = MockMvcRequestBuilders.
                post("/pessoa/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }
    @Test
    void editar() throws Exception {
        Pessoa pessoa = new Pessoa(100l, "Lucas", "", "", 85904000
                ,0,"",85904000
                ,0,"","","");

        String json = objectMapper.writeValueAsString(pessoa);

        RequestBuilder request = MockMvcRequestBuilders.
                put("/pessoa/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }


    @Test
    void deletar() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setIdPessoa(1L);
        String json = objectMapper.writeValueAsString(pessoa);
        RequestBuilder request = MockMvcRequestBuilders.
                delete("/pessoa/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo("OK");
    }

    @Test
    void buscarTodos() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.
                get("/pessoa/getTodos")
                .with(user("rhyan")
                        .password("123"));
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getContentAsByteArray()).isNotNull();
    }

    @Test
    void buscarPorId() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setIdPessoa(1L);
        RequestBuilder request = MockMvcRequestBuilders.
                get("/pessoa/getPorId").param("idPessoa", pessoa.getIdPessoa().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"));

        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }
    @Test
    void testarApiEndereco() throws Exception {
        Map response = pessoaService.criarEndereco(85904000);
        Assertions.assertThat(response.get("status")).isEqualTo("200");
    }

}
