package br.com.gabriel_pereira.educational.model;

import jakarta.persistence.*;

@Entity
@Table(name = "turmas") // Definindo o nome da tabela no banco de dados
public class TurmaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gerar ID automaticamente
    private Integer id;

    @Column(name = "ano", nullable = false) // Coluna "ano", não pode ser nula
    private Integer ano;

    @Column(name = "semestre", nullable = false) // Coluna "semestre", não pode ser nula
    private Integer semestre;

    @ManyToOne(fetch = FetchType.EAGER, optional = false) // Relacionamento obrigatório
    @JoinColumn(name = "curso_id", nullable = false) // Coluna de chave estrangeira
    private CursoModel cursoModel;

    // Construtor vazio necessário para o JPA
    public TurmaModel() {}

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public CursoModel getCursoModel() {
        return cursoModel;
    }

    public void setCursoModel(CursoModel cursoModel) {
        this.cursoModel = cursoModel;
    }
}
