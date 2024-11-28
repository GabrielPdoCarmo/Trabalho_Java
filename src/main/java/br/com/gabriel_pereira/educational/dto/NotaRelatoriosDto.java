package br.com.gabriel_pereira.educational.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;

@JsonPropertyOrder({"id", "nota", "dataLancamento"})
public class NotaRelatoriosDto {

    private Integer id;

    @NotNull(message = "O campo nota não pode ser nulo")
    @PositiveOrZero(message = "O campo nota precisa ser positivo")
    private Double nota;

    @JsonProperty("data_lancamento")
    @NotNull(message = "campo data_lancamento não pode ser nulo")
    @PastOrPresent(message = "data_lancamento não pode ser uma data futura")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dataLancamento;

    @JsonProperty("disciplina")
    private DisciplinaRelatoriosDto disciplinaDto;

    public NotaRelatoriosDto() {
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

    public void setDisciplinaRelatoriosDto(DisciplinaRelatoriosDto disciplinaDto) {
        this.disciplinaDto = disciplinaDto;
    }
}
