package br.com.desafio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("codPauta")
    private Long codPauta;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("voto")
    private String voto;


}
