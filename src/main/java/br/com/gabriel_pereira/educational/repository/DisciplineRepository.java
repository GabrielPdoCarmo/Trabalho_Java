package br.com.gabriel_pereira.educational.repository;

import br.com.gabriel_pereira.educational.model.DisciplineModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisciplineRepository extends JpaRepository<DisciplineModel, Integer> {

    Optional<DisciplineModel> findByCode (String code);

}

