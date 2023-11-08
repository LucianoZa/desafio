package br.com.desafio.controller;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.PautaDTO;
import br.com.desafio.model.RqPautaGet;
import br.com.desafio.service.PautaServiceImpl;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Pauta")
public class PautaControllerImpl implements IpautaController {

	@Autowired
	PautaServiceImpl service;
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<PautaDTO> create(@RequestBody PautaDTO obj) {
		Pauta newPauta = service.create(obj);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/{CodPauta}").buildAndExpand(newPauta.getCodPauta()).toString());
		return ResponseEntity.created(uri).build();
	}

	public ResponseEntity<List<Pauta>> getPauta(
			@ApiParam(value = "CodPauta", required = true) @Valid @PathVariable String codPauta,
			@ApiParam(value = "PautaRequest", required = true) @Valid @RequestBody RqPautaGet rqPautaGet,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		List<Pauta> response = service.getPauta(codPauta, rqPautaGet, PageRequest.of(page, size));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping (value = "/pauta/findAll")
	public ResponseEntity<List<PautaDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		List<Pauta> list = service.findAll(PageRequest.of(page, size));
		List<PautaDTO> listDTO = list.stream().map(x -> mapper.map(x, PautaDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}

}
