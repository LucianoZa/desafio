package br.com.desafio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {

    @JsonProperty("id")
    private BigInteger id;

    @JsonProperty("codPauta")
    private BigInteger codPauta;

    @CPF (message = "Informe um CPF válido")
    @NotNull(message = "Informe o CPF do associado")
    @JsonProperty("cpf")
    private String cpf;

    @NotBlank(message = "Informe seu voto: 'S' para Sim ou 'N' para Não")
    @JsonProperty("voto")
    @Pattern(regexp = "^[S|N]{1}$", message ="Informe 'S' para Sim ou 'N' para Não")
    private String voto;

}
