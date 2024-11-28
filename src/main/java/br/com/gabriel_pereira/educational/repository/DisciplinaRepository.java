package br.com.gabriel_pereira.educational.repository;

import br.com.gabriel_pereira.educational.model.DisciplinaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<DisciplinaModel, Integer> {

    Optional<DisciplinaModel> findByCodigo (String codigo);

}

