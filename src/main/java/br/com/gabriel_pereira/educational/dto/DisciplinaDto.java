package br.com.gabriel_pereira.educational.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonPropertyOrder({"id", "nome", "codigo", "CursoDto", "professorDto"})
public class DisciplinaDto {

    private Integer id;

    @JsonProperty("nome")
    @NotBlank(message = "campo nome não pode ser nulo")
    private String nome;

    @JsonProperty("codigo")
    @NotBlank(message = "campo codigo não pode ser nulo")
    private String codigo;

    @JsonProperty(value = "curso_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "campo curso_id não pode ser nulo")
    private Integer cursoId;

    @JsonProperty(value = "professor_id", access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "campo professor_id não pode ser nulo")
    private Integer professorId;

    @JsonProperty("curso")
    private CursoDto CursoDto;

    @JsonProperty("professor")
    private ProfessorDto professorDto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    public CursoDto getCursoDto() {
        return CursoDto;
    }

    public void setCursoDto(CursoDto CursoDto) {
        this.CursoDto = CursoDto;
    }

    public ProfessorDto getProfessorDto() {
        return professorDto;
    }

    public void setProfessorDto(ProfessorDto professorDto) {
        this.professorDto = professorDto;
    }
}
