package com.recuperacao.demo.repository;

import com.recuperacao.demo.models.AreaConhecimento;
import com.recuperacao.demo.models.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryExemplar extends JpaRepository<Exemplar, Long> {


List<Exemplar> findByDisponivelAndLivro_idLivro(boolean Disponivel, long idLivro);
List<Exemplar> findByDisponivel(boolean Disponivel);
}

