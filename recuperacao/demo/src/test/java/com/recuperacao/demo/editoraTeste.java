package com.recuperacao.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.recuperacao.demo.controller.AreaConhecimentoController;
import com.recuperacao.demo.models.*;
import com.recuperacao.demo.repository.RepositoryEditora;
import com.recuperacao.demo.repository.RepositoryLivro;
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

import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
class editoraTeste {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AreaConhecimentoController controller;
    @Autowired
    private RepositoryLivro livroRepository;
    @Autowired
    private RepositoryEditora repositoryEditora;

    void criarEditoraLivro(){
        Editora editora = criaEditora();
        repositoryEditora.save(editora);
        Livro livro = new Livro(1L, "TESTE", editora, null, null, null);
        Livro livro2 = new Livro(2l, "TESTE", editora, null, null, null);
        Livro livro3 = new Livro(3l, "TESTE", editora, null, null, null);
        livroRepository.saveAll(Arrays.asList(livro,livro2,livro3));
    }

    Editora criaEditora(){
        Editora editora = new Editora(1L, "EditoraTEste", 0);
        return editora;
    }

    @Test
    void criar() throws Exception {
        Editora editora = criaEditora();
        String json = objectMapper.writeValueAsString(editora);
        RequestBuilder request = MockMvcRequestBuilders.
                post("/editora/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void editar() throws Exception {
        Editora editora = criaEditora();;
        editora.setIdEditora(1L);
        String json = objectMapper.writeValueAsString(editora);
        RequestBuilder request = MockMvcRequestBuilders.
                put("/editora/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }


    @Test
    void deletar() throws Exception {
        Editora editora = criaEditora();
        String json = objectMapper.writeValueAsString(editora);
        RequestBuilder request = MockMvcRequestBuilders.
                delete("/editora/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo("OK");
    }

    @Test
    void buscarTodos() throws Exception {
        criarEditoraLivro();
        RequestBuilder request = MockMvcRequestBuilders.
                get("/editora/buscarTodos")
                .with(user("rhyan")
                        .password("123"));
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getContentAsString()).isNotNull();
    }

    @Test
    void buscarPorId() throws Exception {
        Editora editora = criaEditora();
        editora.setIdEditora(1L);
        RequestBuilder request = MockMvcRequestBuilders.
                get("/editora/getPorId").param("idEditora", editora.getIdEditora().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"));

        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

}
