package br.com.gabriel_pereira.educational.repository;

import br.com.gabriel_pereira.educational.model.ProfessorModel;
import br.com.gabriel_pereira.educational.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<ProfessorModel, Integer> {

    Optional<ProfessorModel> findByEmail(String email);
}
