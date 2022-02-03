package com.recuperacao.demo.repository;

import com.recuperacao.demo.models.AreaConhecimento;
import com.recuperacao.demo.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAutor extends JpaRepository<Autor, Long> {

}

