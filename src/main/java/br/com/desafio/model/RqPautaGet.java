package br.com.desafio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RqPautaGet {

	  @JsonProperty("statusPauta")
	  private String statusPauta = null;
}
