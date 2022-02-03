package com.recuperacao.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.recuperacao.demo.controller.AreaConhecimentoController;
import com.recuperacao.demo.models.AreaConhecimento;
import com.recuperacao.demo.models.Autor;
import com.recuperacao.demo.models.Categoria;
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
class CategoriaTeste {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AreaConhecimentoController controller;

    @Test
    void criar() throws Exception {
        Categoria categoria = new Categoria();
        String json = objectMapper.writeValueAsString(categoria);
        RequestBuilder request = MockMvcRequestBuilders.
                post("/categoria/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void editar() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1L);
        String json = objectMapper.writeValueAsString(categoria);
        RequestBuilder request = MockMvcRequestBuilders.
                put("/categoria/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }


    @Test
    void deletar() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1L);
        String json = objectMapper.writeValueAsString(categoria);
        RequestBuilder request = MockMvcRequestBuilders.
                delete("/categoria/delete")
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
                get("/categoria/getTodos")
                .with(user("rhyan")
                        .password("123"));
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getContentAsByteArray()).isNotNull();
    }

    @Test
    void buscarPorId() throws Exception {
        Categoria Categoria = new Categoria();
        Categoria.setIdCategoria(1L);
        RequestBuilder request = MockMvcRequestBuilders.
                get("/categoria/getPorId").param("idCategoria", Categoria.getIdCategoria().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"));

        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

}
