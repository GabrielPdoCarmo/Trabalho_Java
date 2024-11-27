package br.com.gabriel_pereira.educational.repository;

import br.com.gabriel_pereira.educational.model.CourseModel;
import br.com.gabriel_pereira.educational.model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseModel, Integer> {

    Optional<CourseModel> findByCode (String code);

}
