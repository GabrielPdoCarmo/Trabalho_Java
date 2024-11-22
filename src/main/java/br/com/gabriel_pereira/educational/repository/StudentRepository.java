package br.com.gabriel_pereira-main.educational.repository;

import br.com.gabriel_pereira-main.educational.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

    Optional<StudentModel> findByEmail(String email);
}