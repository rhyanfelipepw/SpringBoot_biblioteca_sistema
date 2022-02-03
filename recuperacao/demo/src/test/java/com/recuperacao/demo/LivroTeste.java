package com.recuperacao.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.recuperacao.demo.controller.AreaConhecimentoController;
import com.recuperacao.demo.models.Livro;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
class LivroTeste {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AreaConhecimentoController controller;

    @Test
    void criar() throws Exception {
        Livro livro = new Livro();
        String json = objectMapper.writeValueAsString(livro);
        RequestBuilder request = MockMvcRequestBuilders.
                post("/livro/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }
    @Test
    void editar() throws Exception {
        Livro livro = new Livro();
        livro.setIdLivro(1L);
        String json = objectMapper.writeValueAsString(livro);
        RequestBuilder request = MockMvcRequestBuilders.
                put("/livro/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }


    @Test
    void deletar() throws Exception {
        Livro livro = new Livro();
        livro.setIdLivro(1L);
        String json = objectMapper.writeValueAsString(livro);
        RequestBuilder request = MockMvcRequestBuilders.
                delete("/livro/delete")
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
                get("/livro/getTodos")
                .with(user("rhyan")
                        .password("123"));
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getContentAsByteArray()).isNotNull();
    }

    @Test
    void buscarPorId() throws Exception {
        Livro livro = new Livro();
        livro.setIdLivro(1L);
        RequestBuilder request = MockMvcRequestBuilders.
                get("/livro/getPorId").param("idLivro", livro.getIdLivro().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"));

        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }
}
