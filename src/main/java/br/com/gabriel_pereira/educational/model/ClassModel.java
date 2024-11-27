package br.com.gabriel_pereira.educational.model;

import jakarta.persistence.*;

@Entity
@Table(name = "turmas") // Definindo o nome da tabela no banco de dados
public class ClassModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gerar ID automaticamente
    private Integer id;

    @Column(name = "ano", nullable = false) // Coluna "ano", não pode ser nula
    private Integer year;

    @Column(name = "semestre", nullable = false) // Coluna "semestre", não pode ser nula
    private Integer semester;

    @ManyToOne(fetch = FetchType.EAGER, optional = false) // Relacionamento obrigatório
    @JoinColumn(name = "curso_id", nullable = false) // Coluna de chave estrangeira
    private CourseModel courseModel;

    // Construtor vazio necessário para o JPA
    public ClassModel() {}

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public CourseModel getCourseModel() {
        return courseModel;
    }

    public void setCourseModel(CourseModel courseModel) {
        this.courseModel = courseModel;
    }
}
