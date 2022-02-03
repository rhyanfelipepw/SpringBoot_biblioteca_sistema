package com.recuperacao.demo.repository;

import com.recuperacao.demo.models.AreaConhecimento;
import com.recuperacao.demo.models.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryEditora extends JpaRepository<Editora, Long> {

}

