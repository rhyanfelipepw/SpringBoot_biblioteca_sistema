package com.recuperacao.demo.repository;

import com.recuperacao.demo.models.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface RepositoryEmprestimo extends JpaRepository<Emprestimo, Long> {
    @Query(value = "SELECT e FROM Emprestimo e where e.dataDevolucao > ?1 and e.devolvido = false")
    Collection<Emprestimo> FindAtrasados(Date date);
    List<Emprestimo> findByDevolvidoAndPessoa_idPessoa(boolean devolvido,long idPessoa);
    List<Emprestimo> findByDevolvido(boolean devolvido);
}

