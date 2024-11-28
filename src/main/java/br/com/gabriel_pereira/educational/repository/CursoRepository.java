package br.com.gabriel_pereira.educational.repository;

import br.com.gabriel_pereira.educational.model.CursoModel;
import br.com.gabriel_pereira.educational.model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<CursoModel, Integer> {

    Optional<CursoModel> findByCodigo (String codigo);

}
