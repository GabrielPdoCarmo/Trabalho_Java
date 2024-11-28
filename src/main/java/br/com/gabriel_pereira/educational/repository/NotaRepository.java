package br.com.gabriel_pereira.educational.repository;

import br.com.gabriel_pereira.educational.model.NotaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotaRepository extends JpaRepository<NotaModel, Integer> {
    @Query("SELECT n FROM notas n WHERE n.matriculaModel.alunoModel.id = :alunoId")
    Optional<List<NotaModel>> findByAlunoId(@Param("alunoId") Integer alunoId);

    @Query("SELECT n FROM notas n WHERE n.matriculaModel.turmaModel.id = :turmaId")
    Optional<List<NotaModel>> findByTurmaId(@Param("turmaId") Integer turmaId);

    @Query("SELECT n FROM notas n WHERE n.disciplinaModel.id = :disciplinaId")
    Optional<List<NotaModel>> findByDisciplinaId(@Param("disciplinaId") Integer disciplinaId);
}
