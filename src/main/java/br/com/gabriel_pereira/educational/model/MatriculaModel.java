package br.com.gabriel_pereira.educational.model;

import jakarta.persistence.*;

@Entity(name = "matriculas")
public class MatriculaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoModel alunoModel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "turma_id", nullable = false)
    private TurmaModel turmaModel;

    public MatriculaModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AlunoModel getAlunoModel() {
        return alunoModel;
    }

    public void setAlunoModel(AlunoModel alunoModel) {
        this.alunoModel = alunoModel;
    }

    public TurmaModel getTurmaModel() {
        return turmaModel;
    }

    public void setTurmaModel(TurmaModel turmaModel) {
        this.turmaModel = turmaModel;
    }
}