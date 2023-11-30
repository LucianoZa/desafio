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

    @NotBlank(message = "Informe o nome da pauta")
    @JsonProperty("nomPauta")
    private String nomPauta;

    @JsonProperty("dtIniVotacao")
    private LocalDateTime dtIniVotacao;

    @JsonProperty("dtFimVotacao")
    private LocalDateTime dtFimVotacao;

}
