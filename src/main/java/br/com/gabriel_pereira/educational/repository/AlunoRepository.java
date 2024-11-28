package br.com.gabriel_pereira.educational.repository;

import br.com.gabriel_pereira.educational.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<AlunoModel, Integer> {

    Optional<AlunoModel> findByEmail(String email);
}
