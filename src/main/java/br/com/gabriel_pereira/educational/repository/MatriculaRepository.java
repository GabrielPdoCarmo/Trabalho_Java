package br.com.gabriel_pereira.educational.repository;

import br.com.gabriel_pereira.educational.model.TurmaModel;
import br.com.gabriel_pereira.educational.model.MatriculaModel;
import br.com.gabriel_pereira.educational.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface MatriculaRepository extends JpaRepository<MatriculaModel, Integer> {
    Optional<MatriculaModel> findByAlunoModelAndTurmaModel(AlunoModel alunoModel, TurmaModel turmaModel);
}

