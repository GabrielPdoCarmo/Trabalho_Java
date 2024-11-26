package br.com.gabriel_pereira.educational.repository;

import br.com.gabriel_pereira.educational.model.ClassModel;
import br.com.gabriel_pereira.educational.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ClassRepository extends JpaRepository<ClassModel, Integer> {
        @Query("SELECT c FROM ClassModel c " +
                "WHERE (:year IS NULL OR c.year = :year) " +
                "AND (:semester IS NULL OR c.semester = :semester) " +
                "AND (:courseId IS NULL OR c.courseModel.id = :courseId)")
        Set<ClassModel> searchClasses(
                @Param("year") Integer year,
                @Param("semester") Integer semester,
                @Param("courseId") Integer courseId
        );
}


