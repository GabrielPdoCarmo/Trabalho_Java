package br.com.gabriel_pereira.educational.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"disciplinaDto", "mediaNotas"})
public class MediaDisciplinaDto {

    @JsonProperty(value = "Diciplina")
    private DisciplinaDto disciplinaDto;

    @JsonProperty(value = "media_notas")
    private Double mediaNotas;

    public MediaDisciplinaDto(DisciplinaDto disciplinaDto, Double mediaNotas) {
        this.disciplinaDto = disciplinaDto;
        this.mediaNotas = mediaNotas;
    }

}
