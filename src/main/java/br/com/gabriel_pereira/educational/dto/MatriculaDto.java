package br.com.gabriel_pereira.educational.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder({"id", "alunoId", "turmaId"})
public class MatriculaDto{

    private Integer id;

    @JsonProperty(value = "aluno_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "campo aluno_id não pode ser nulo")
    private Integer alunoId;

    @JsonProperty(value = "turma_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "o campo  turma_id não pode ser nulo")
    private Integer turmaId;

    @JsonProperty("aluno")
    private AlunoDto AlunoDto;

    @JsonProperty("turma")
    private TurmaDto TurmaDto;

    public MatriculaDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Integer alunoId) {
        this.alunoId = alunoId;
    }

    public Integer getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Integer turmaId) {
        this.turmaId = turmaId;
    }

    public AlunoDto getAlunoDto() {
        return AlunoDto;
    }

    public void setAlunoDto(AlunoDto AlunoDto) {
        this.AlunoDto = AlunoDto;
    }

    public TurmaDto getTurmaDto() {
        return TurmaDto;
    }

    public void setTurmaDto(TurmaDto TurmaDto) {
        this.TurmaDto = TurmaDto;
    }
}
