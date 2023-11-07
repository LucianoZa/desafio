package br.com.desafio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO {

    @JsonProperty("codPauta")
    private Long codPauta;

    @JsonProperty("nomPauta")
    private String nomPauta;

    @JsonProperty("dtIniVotacao")
    private LocalDateTime dtIniVotacao;

    @JsonProperty("dtFimVotacao")
    private LocalDateTime dtFimVotacao;

}
