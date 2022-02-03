package com.recuperacao.demo.repository;

import com.recuperacao.demo.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPessoa extends JpaRepository<Pessoa, Long> {

}

