package com.recuperacao.demo.repository;

import com.recuperacao.demo.models.Emprestimo;
import com.recuperacao.demo.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryLivro extends JpaRepository<Livro, Long> {
    List<Livro> findByEditora_idEditora(long idEditora);

}

