package br.com.desafio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RsPautaAdd {

	  @JsonProperty("codPauta")
	  private Long codPauta = null;

	  @JsonProperty("nomPauta")
	  private String nomPauta = null;

}
