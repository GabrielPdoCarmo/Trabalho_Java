package br.com.gabriel_pereira.educational.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"TurmaDto", "mediaNotas"})
public class MediaTurmaDto {

    @JsonProperty(value = "Turma")
    private TurmaDto TurmaDto;

    @JsonProperty(value = "media_notas")
    private Double mediaNotas;

    public MediaTurmaDto(TurmaDto TurmaDto, Double mediaNotas) {
        this.TurmaDto = TurmaDto;
        this.mediaNotas = mediaNotas;
    }
}
