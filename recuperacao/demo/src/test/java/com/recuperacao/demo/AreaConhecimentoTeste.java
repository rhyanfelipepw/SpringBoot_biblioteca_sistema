package com.recuperacao.demo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recuperacao.demo.controller.AreaConhecimentoController;
import com.recuperacao.demo.models.AreaConhecimento;
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
import org.springframework.web.servlet.function.EntityResponse;

import javax.net.ssl.SSLEngineResult;

import java.lang.reflect.Array;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
class AreaConhecimentoTeste {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AreaConhecimentoController controller;

    @Test
    void criar() throws Exception {
        AreaConhecimento area = new AreaConhecimento();
        String json = objectMapper.writeValueAsString(area);
        RequestBuilder request = MockMvcRequestBuilders.
                post("/areaConhecimento/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void editar() throws Exception {
        AreaConhecimento area = new AreaConhecimento();
        area.setIdAreaConhecimento(1L);
        String json = objectMapper.writeValueAsString(area);
        RequestBuilder request = MockMvcRequestBuilders.
                put("/areaConhecimento/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }


    @Test
    void deletar() throws Exception {
        AreaConhecimento area = new AreaConhecimento();
        area.setIdAreaConhecimento(1L);
        String json = objectMapper.writeValueAsString(area);
        RequestBuilder request = MockMvcRequestBuilders.
                delete("/areaConhecimento/delete")
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
                get("/areaConhecimento/getTodos")
                .with(user("rhyan")
                        .password("123"));
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getContentAsByteArray()).isNotNull();
    }

    @Test
    void buscarPorId() throws Exception {
        AreaConhecimento area = new AreaConhecimento();
        area.setIdAreaConhecimento(1L);
        RequestBuilder request = MockMvcRequestBuilders.
                get("/areaConhecimento/getPorId").param("idAreaConhecimento", area.getIdAreaConhecimento().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"));

        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

}
