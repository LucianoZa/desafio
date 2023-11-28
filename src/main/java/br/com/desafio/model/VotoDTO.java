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

    @CPF (message = "{cpf.invalido}")
    @NotNull(message = "{cpf.not.null}")
    @JsonProperty("cpf")
    private String cpf;

    @NotBlank(message = "{voto.not.blank}")
    @JsonProperty("voto")
    @Pattern(regexp = "^[S|N]{1}$", message ="{voto.invalido}")
    private String voto;

}
