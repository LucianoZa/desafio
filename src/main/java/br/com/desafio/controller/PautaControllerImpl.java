package br.com.desafio.controller;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.PautaDTO;
import br.com.desafio.service.PautaServiceImpl;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

	public ResponseEntity<PautaDTO> findById(
			@ApiParam(value = "codPauta", required = true) @Valid @PathVariable Long codPauta) {
		return ResponseEntity.ok().body(mapper.map(service.findById(codPauta), PautaDTO.class));
	}

	public ResponseEntity<List<PautaDTO>> findByDtIniVotacaoIsNull(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		List<Pauta> list = service.findByDtIniVotacaoIsNull(PageRequest.of(page, size));
		List<PautaDTO> listDTO = list.stream().map(x -> mapper.map(x, PautaDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	public ResponseEntity<List<PautaDTO>> findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		List<Pauta> list = service.findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(PageRequest.of(page, size));
		List<PautaDTO> listDTO = list.stream().map(x -> mapper.map(x, PautaDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	public ResponseEntity<List<PautaDTO>> findByDtFimVotacaoIsNotNull(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		List<Pauta> list = service.findByDtFimVotacaoIsNotNull(PageRequest.of(page, size));
		List<PautaDTO> listDTO = list.stream().map(x -> mapper.map(x, PautaDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}


	public ResponseEntity<List<PautaDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size) {
		List<Pauta> list = service.findAll(PageRequest.of(page, size));
		List<PautaDTO> listDTO = list.stream().map(x -> mapper.map(x, PautaDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}


}
