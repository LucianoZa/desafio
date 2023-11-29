package br.com.desafio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class PautaDTO {

    @JsonProperty("codPauta")
    private Long id;

    @NotBlank(message = "{pauta.nome.not.blank}")
    @JsonProperty("nomPauta")
    private String nomPauta;

    @JsonProperty("dtIniVotacao")
    private LocalDateTime dtIniVotacao;

    @JsonProperty("dtFimVotacao")
    private LocalDateTime dtFimVotacao;

}
