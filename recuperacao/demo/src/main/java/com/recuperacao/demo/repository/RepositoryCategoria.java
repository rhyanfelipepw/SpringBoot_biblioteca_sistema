package com.recuperacao.demo.repository;

import com.recuperacao.demo.models.Categoria;
import com.recuperacao.demo.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCategoria extends JpaRepository<Categoria, Long> {

}

