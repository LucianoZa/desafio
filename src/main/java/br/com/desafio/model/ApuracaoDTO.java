package br.com.desafio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApuracaoDTO {

    @JsonProperty("cod_Pauta")
    private BigInteger cod_Pauta;

    @JsonProperty("votosTotalSim")
    private BigInteger votosTotalSim;

    @JsonProperty("votosTotal")
    private BigInteger votosTotal;

}
