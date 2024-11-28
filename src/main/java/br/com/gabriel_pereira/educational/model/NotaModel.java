package br.com.gabriel_pereira.educational.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "notas")
public class NotaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nota")
    private Double nota;

    @Column(name = "data_lancamento")
    @Temporal(TemporalType.DATE)
    private Date dataLancamento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "matricula_id", nullable = false)
    private MatriculaModel matriculaModel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "disciplina_id", nullable = false)
    private DisciplinaModel disciplinaModel;

    public NotaModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public MatriculaModel getMatriculaModel() {
        return matriculaModel;
    }

    public void setMatriculaModel(MatriculaModel matriculaModel) {
        this.matriculaModel = matriculaModel;
    }

    public DisciplinaModel getDisciplinaModel() {
        return disciplinaModel;
    }

    public void setDisciplinaModel(DisciplinaModel disciplinaModel) {
        this.disciplinaModel = disciplinaModel;
    }
}
