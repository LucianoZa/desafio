package br.com.desafio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RqPautaAdd {

	  @JsonProperty("nomPauta")
	  private String nomPauta = null;

	
}
