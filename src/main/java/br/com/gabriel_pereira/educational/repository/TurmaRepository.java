package br.com.gabriel_pereira.educational.repository;

import br.com.gabriel_pereira.educational.model.TurmaModel;
import br.com.gabriel_pereira.educational.model.CursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TurmaRepository extends JpaRepository<TurmaModel, Integer> {
        @Query("SELECT c FROM TurmaModel c " +
                "WHERE (:ano IS NULL OR c.ano = :ano) " +
                "AND (:semestre IS NULL OR c.semestre = :semestre) " +
                "AND (:cursoId IS NULL OR c.CursoModel.id = :cursoId)")
        Set<TurmaModel> searchClasses(
                @Param("ano") Integer ano,
                @Param("semestre") Integer semestre,
                @Param("cursoId") Integer cursoId
        );
}


