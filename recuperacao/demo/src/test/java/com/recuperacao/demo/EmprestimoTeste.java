package com.recuperacao.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.recuperacao.demo.controller.AreaConhecimentoController;
import com.recuperacao.demo.controller.EmprestimoController;
import com.recuperacao.demo.models.*;
import com.recuperacao.demo.repository.RepositoryCategoria;
import com.recuperacao.demo.repository.RepositoryEmprestimo;
import com.recuperacao.demo.repository.RepositoryLivro;
import com.recuperacao.demo.repository.RepositoryPessoa;
import com.recuperacao.demo.service.ExemplarService;
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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
class EmprestimoTeste {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmprestimoController controller;

    @Autowired
    private RepositoryEmprestimo repositoryEmprestimo;

    @Autowired
    private RepositoryPessoa repositoryPessoa;

    @Autowired
    RepositoryLivro repositoryLivro;

    @Autowired
    RepositoryCategoria repositoryCategoria;

    @Autowired
    private ExemplarService exemplarService;

    @Test
    void criar() throws Exception {
        Emprestimo emprestimo = getEmprestimoInstanciado();
        String json = objectMapper.writeValueAsString(emprestimo);
        RequestBuilder request = MockMvcRequestBuilders.
                post("/emprestimo/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);

        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    Emprestimo getEmprestimoInstanciado(){
        Calendar cal = Calendar.getInstance();
        Date dataEmprestimo  =cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date dataDevolucao = cal.getTime();
        Pessoa pessoa = new Pessoa(1L, "", "", "", 0,0,"",0,0,"","","");
        Pessoa responsePessoa = repositoryPessoa.save(pessoa);
        Categoria categoria = new Categoria(1L,"Teste", 10);
        repositoryCategoria.save(categoria);
        Livro livro = new Livro(1L, "TESTE", null, null, categoria, null);
        repositoryLivro.save(livro);
        Exemplar exemplar = new Exemplar(1L, true, livro);
        exemplarService.criarExemplar(exemplar);
        Emprestimo emprestimo = new Emprestimo(1L, dataEmprestimo, dataDevolucao, false, responsePessoa, exemplar);
        return emprestimo;
    };



    @Test
    void usuarioJaComEmprestimo() throws Exception {
        Emprestimo emprestimo = getEmprestimoInstanciado();
        String json = objectMapper.writeValueAsString(emprestimo);
        RequestBuilder request = MockMvcRequestBuilders.
                post("/emprestimo/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);

        MvcResult result = mockMvc.perform(request).andReturn();

        RequestBuilder tete = MockMvcRequestBuilders.
                post("/emprestimo/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);

        MvcResult resulttete = mockMvc.perform(tete).andReturn();
        Assertions.assertThat(resulttete.getResponse().getStatus()).isEqualTo(400);
    }

    @Test
    void usuarioDiferenteSemExemplar() throws Exception {
        Emprestimo emprestimo = getEmprestimoInstanciado();
        String json = objectMapper.writeValueAsString(emprestimo);
        RequestBuilder request = MockMvcRequestBuilders.
                post("/emprestimo/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);

        MvcResult result = mockMvc.perform(request).andReturn();
        emprestimo.getPessoa().setIdPessoa(2L);
        String json1 = objectMapper.writeValueAsString(emprestimo);
        RequestBuilder tete = MockMvcRequestBuilders.
                post("/emprestimo/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json1);

        MvcResult resulttete = mockMvc.perform(tete).andReturn();
        Assertions.assertThat(resulttete.getResponse().getStatus()).isEqualTo(400);
    }



    @Test
    void editar() throws Exception {
        Emprestimo emprestimo = getEmprestimoInstanciado();
        emprestimo.setIdEmprestimo(1L);
        String json = objectMapper.writeValueAsString(emprestimo);
        RequestBuilder request = MockMvcRequestBuilders.
                put("/emprestimo/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();


        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }


    @Test
    void deletar() throws Exception {
        Emprestimo emprestimo = getEmprestimoInstanciado();
        emprestimo.setIdEmprestimo(1L);
        String json = objectMapper.writeValueAsString(emprestimo);
        RequestBuilder request = MockMvcRequestBuilders.
                delete("/emprestimo/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"))
                .content(json);
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo("OK");
    }

    @Test
    void buscarTodos() throws Exception {
        Emprestimo emprestimo = getEmprestimoInstanciado();
        emprestimo.setIdEmprestimo(1L);
        RequestBuilder request = MockMvcRequestBuilders.
                get("/emprestimo/buscarTodos")
                .with(user("rhyan")
                        .password("123"));
        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getContentAsByteArray()).isNotNull();
    }

    @Test
    void buscarPorId() throws Exception {

        Emprestimo emprestimo = getEmprestimoInstanciado();
        emprestimo.setIdEmprestimo(1L);
        RequestBuilder request = MockMvcRequestBuilders.
                get("/emprestimo/getPorId").param("idEmprestimo", emprestimo.getIdEmprestimo().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"));

        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }


    @Test
    void buscarTodosAtrasados() throws Exception {
        criar();
        RequestBuilder request = MockMvcRequestBuilders.
                get("/emprestimo/getAtrasados")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("rhyan")
                        .password("123"));

        MvcResult result = mockMvc.perform(request).andReturn();
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }
}
