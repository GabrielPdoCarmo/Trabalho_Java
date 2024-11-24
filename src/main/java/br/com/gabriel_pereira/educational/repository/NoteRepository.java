package br.com.gabriel_pereira.educacional.repository;

import br.com.gabriel_pereira.educacional.model.ClassModel;
import br.com.gabriel_pereira.educacional.model.MatriculationModel;
import br.com.gabriel_pereira.educacional.model.NoteModel;
import br.com.gabriel_pereira.educacional.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<NoteModel, Integer> {

}

