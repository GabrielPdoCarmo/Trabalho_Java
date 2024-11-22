package br.com.gabriel_pereira.educacional.repository;

import br.com.gabriel_pereira.educacional.model.CourseModel;
import br.com.gabriel_pereira.educacional.model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseModel, Integer> {

    Optional<CourseModel> findByCode (String code);

}
