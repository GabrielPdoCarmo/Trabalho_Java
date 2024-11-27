package br.com.gabriel_pereira.educational.repository;

import br.com.gabriel_pereira.educational.model.ClassModel;
import br.com.gabriel_pereira.educational.model.MatriculationModel;
import br.com.gabriel_pereira.educational.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface MatriculationRepository extends JpaRepository<MatriculationModel, Integer> {
    Optional<MatriculationModel> findByStudentModelAndClassModel(StudentModel studentModel, ClassModel classModel);
}

