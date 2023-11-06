package br.com.desafio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RqPautaAdd {

	  @JsonProperty("nomPauta")
	  private String nomPauta = null;

	
}
