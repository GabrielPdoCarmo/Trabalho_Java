package br.com.gabriel_pereira.educational.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"DisciplinaDto", "mediaNotas"})
public class MediaDisciplinaDto {

    @JsonProperty(value = "Diciplina")
    private DisciplinaDto DisciplinaDto;

    @JsonProperty(value = "media_notas")
    private Double mediaNotas;

    public MediaDisciplinaDto(DisciplinaDto DisciplinaDto, Double mediaNotas) {
        this.DisciplinaDto = DisciplinaDto;
        this.mediaNotas = mediaNotas;
    }

}
