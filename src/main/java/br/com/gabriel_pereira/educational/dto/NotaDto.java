package br.com.gabriel_pereira.educational.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;

@JsonPropertyOrder({"id", "nota", "dataLancamento", "matriculaId", "disciplinaId"})
public class NotaDto {

    private Integer id;

    @NotNull(message = "O campo nota não pode ser nulo")
    @PositiveOrZero(message = "O campo nota precisa ser positivo")
    private Double nota;

    @JsonProperty("data_lancamento")
    @NotNull(message = "campo data_lancamento não pode ser nulo")
    @PastOrPresent(message = "data_lancamento não pode ser uma data futura")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataLancamento;

    @JsonProperty(value = "matricula_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "campo matricula_id não pode ser nulo")
    private Integer matriculaId;

    @JsonProperty(value = "disciplina_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "o campo  disciplina_id não pode ser nulo")
    private Integer disciplinaId;

    @JsonProperty("matricula")
    private MatriculaDto matriculaDto;

    @JsonProperty("disciplina")
    private DisciplinaDto disciplinaDto;

    public NotaDto() {
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

    public Integer getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(Integer matriculaId) {
        this.matriculaId = matriculaId;
    }

    public Integer getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Integer disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public MatriculaDto getMatriculaDto() {
        return matriculaDto;
    }

    public void setMatriculaDto(MatriculaDto matriculaDto) {
        this.matriculaDto= matriculaDto;
    }

    public DisciplinaDto getDisciplinaDto() {
        return disciplinaDto;
    }

    public void setDisciplinaDto(DisciplinaDto disciplinaDto) {
        this.disciplinaDto = disciplinaDto;
    }
}
