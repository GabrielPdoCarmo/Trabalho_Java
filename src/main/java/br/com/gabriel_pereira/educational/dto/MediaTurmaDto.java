package br.com.gabriel_pereira.educational.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"turmaDto", "mediaNotas"})
public class MediaTurmaDto {

    @JsonProperty(value = "Turma")
    private TurmaDto turmaDto;

    @JsonProperty(value = "media_notas")
    private Double mediaNotas;

    public MediaTurmaDto(TurmaDto turmaDto, Double mediaNotas) {
        this.turmaDto = turmaDto;
        this.mediaNotas = mediaNotas;
    }
}
