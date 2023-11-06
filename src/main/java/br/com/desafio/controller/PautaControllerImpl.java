package br.com.desafio.controller;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.RqPautaAdd;
import br.com.desafio.model.RqPautaGet;
import br.com.desafio.model.RsPautaAdd;
import br.com.desafio.service.PautaServiceImpl;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Pauta")
public class PautaControllerImpl implements IpautaController {

	@Autowired
	PautaServiceImpl service;

	public ResponseEntity<RsPautaAdd> addPauta(
			@ApiParam(value = "Pauta Add Request", required = true) @Valid @RequestBody RqPautaAdd rqPautaAdd) {
		RsPautaAdd response = service.addPauta(rqPautaAdd);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<List<Pauta>> getPauta(
			@ApiParam(value = "CodPauta", required = true) @Valid @PathVariable String codPauta,
			@ApiParam(value = "PautaRequest", required = true) @Valid @RequestBody RqPautaGet rqPautaGet,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		List<Pauta> response = service.getPauta(codPauta, rqPautaGet, PageRequest.of(page, size));
		return new ResponseEntity<List<Pauta>>(response, HttpStatus.OK);
	}

}
