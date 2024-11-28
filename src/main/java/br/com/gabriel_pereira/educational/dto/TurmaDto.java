package br.com.gabriel_pereira.educational.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@JsonPropertyOrder({"id", "ano", "semestre", "CursoDto"})
public class TurmaDto {

    private Integer id;

    @JsonProperty("ano")
    @NotNull(message = "campo ano não pode ser nulo")
    @Positive(message = "o campo ano só pode ser positivo")
    private Integer ano;

    @JsonProperty("semestre")
    @Min(value = 1, message = "o campo semestre só pode ser 1 ou 2")
    @Max(value = 2, message = "o campo semestre só pode ser 1 ou 2")
    private Integer semestre;

    @JsonProperty(value = "curso_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "campo curso_id não pode ser nulo")
    private Integer cursoId;

    @JsonProperty("curso")
    private CursoDto CursoDto;

    public TurmaDto() {
    }

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

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public CursoDto getCursoDto() {
        return CursoDto;
    }

    public void setCursoDto(CursoDto CursoDto) {
        this.CursoDto = CursoDto;
    }
}