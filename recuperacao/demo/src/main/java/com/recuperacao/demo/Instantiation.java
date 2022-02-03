package com.recuperacao.demo;

import com.recuperacao.demo.models.*;
import com.recuperacao.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Configuration
public class Instantiation  implements CommandLineRunner {
    @Autowired
    RepositoryAutor repositoryAutor;

    @Autowired
    RepositoryCategoria repositoryCategoria;

    @Autowired
    RepositoryEditora repositoryEditora;

    @Autowired
    RepositoryLivro repositoryLivro;

    @Autowired
    RepositoryExemplar repositoryExemplar;

    @Autowired
    RepositoryEmprestimo repositoryEmprestimo;

    @Autowired
    RepositoryPessoa repositoryPessoa;

    @Autowired
    RepositoryAreaConhecimento repositoryAreaConhecimento;

    @Override
    public void run(String... args) throws Exception {


        Pessoa pessoa = new Pessoa(1L, "Rhyan", "", "", 0,0,"",0,0,"","","");
        Pessoa pessoa2 = new Pessoa(2L, "Whindersson", "", "", 0,0,"",0,0,"","","");
        Pessoa pessoa3 = new Pessoa(3L, "Lucas", "", "", 0,0,"",0,0,"","","");

        repositoryPessoa.saveAll(Arrays.asList(pessoa,pessoa2,pessoa3));
        Autor autor1 = new Autor(1l, "Autor1");
        Autor autor2 = new Autor(2l, "Autor2");
        Autor autor3 = new Autor(3l, "Autor3");
        repositoryAutor.saveAll(Arrays.asList(autor1, autor2, autor3));

        Categoria categoria = new Categoria(1l, "Ação", 5);
        Categoria categoria2 = new Categoria(2l, "Romance", 10);
        Categoria categoria3 = new Categoria(3l, "Drama", 15);
        repositoryCategoria.saveAll(Arrays.asList(categoria, categoria2, categoria3));

        Editora editora = new Editora(1L, "Editora1", 0);
        Editora editora2 = new Editora(2l, "Editora2", 0);
        Editora editora3 = new Editora(3l, "Editora3", 0);
        repositoryEditora.saveAll(Arrays.asList(editora, editora2, editora3));

        Livro livro1 = new Livro(1l, "livro1", editora, autor1, categoria, null);
        Livro livro2 = new Livro(2l, "livro2", editora2, autor2, categoria, null);
        Livro livro3 = new Livro(3l, "livro3", editora3, autor3, categoria, null);
        Livro livro4 = new Livro(4l, "livro4", editora2, autor2, categoria, null);
        Livro livro5 = new Livro(5l, "livro5", editora3, autor3, categoria, null);

        repositoryLivro.saveAll(Arrays.asList(livro1, livro2, livro3, livro4, livro5));

        AreaConhecimento areaConhecimento = new AreaConhecimento(1L, "FILOSOFIA", null);
        repositoryAreaConhecimento.save(areaConhecimento);

        Exemplar exemplar1 = new Exemplar(1l, true, livro1);
        Exemplar exemplar2 = new Exemplar(2l, true, livro1);
        Exemplar exemplar3 = new Exemplar(3l, true, livro1);

        Exemplar exemplar4 = new Exemplar(4l, true, livro2);
        Exemplar exemplar5 = new Exemplar(5l, true, livro2);



        repositoryExemplar.saveAll(Arrays.asList(exemplar1, exemplar2,
                exemplar3, exemplar4, exemplar5));

        Calendar cal = Calendar.getInstance();
        Date dataEmprestimo =cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date dataDevolucao = cal.getTime();

        Emprestimo emp1 = new Emprestimo(1L, dataEmprestimo, dataDevolucao, false, pessoa2, exemplar1);
        Emprestimo emp2 = new Emprestimo(2L, dataEmprestimo, dataDevolucao, false, pessoa2, exemplar2);
        Emprestimo emp3 = new Emprestimo(3L, dataEmprestimo, dataDevolucao, false, pessoa3, exemplar3);
        Emprestimo emp4 = new Emprestimo(4L, dataEmprestimo, dataEmprestimo, false, pessoa3, exemplar3);
        Emprestimo emp5 = new Emprestimo(5L, dataEmprestimo, dataEmprestimo, false, pessoa3, exemplar3);




        repositoryEmprestimo.saveAll(Arrays.asList(emp1,emp2,emp3,emp4,emp5));
    }


}
