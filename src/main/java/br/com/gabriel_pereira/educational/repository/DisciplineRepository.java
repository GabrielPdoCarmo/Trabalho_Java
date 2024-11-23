package br.com.gabriel_pereira.educacional.repository;

import br.com.gabriel_pereira.educacional.model.DisciplineModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisciplineRepository extends JpaRepository<DisciplineModel, Integer> {

    Optional<DisciplineModel> findByCode (String code);

}

