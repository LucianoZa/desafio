package br.com.desafio.controller;

import br.com.desafio.domain.entity.Voto;
import br.com.desafio.model.ApuracaoDTO;
import br.com.desafio.model.VotoDTO;
import br.com.desafio.service.VotoServiceImpl;
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
@Tag(name = "Votação")
public class VotoControllerImpl implements IvotoController {

	public static final String PAGE_DEFAULT = "0";
	public static final String SIZE_DEFAULT = "10";
	public static final String PATH = "/{votacao}";
	@Autowired
	VotoServiceImpl service;

	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<VotoDTO> create(
			@RequestBody VotoDTO obj,
			@ApiParam(value = "codPauta", required = true) @Valid @PathVariable Long codPauta) {
		obj.setCodPauta(codPauta);
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path(PATH)
					.buildAndExpand(service.create(obj).getId()).toString());
		return ResponseEntity.created(uri).build();
	}

	public ResponseEntity<List<VotoDTO>> findByCodPauta(
			@ApiParam(value = "codPauta", required = true) @Valid @PathVariable Long codPauta,
			@RequestParam(value = "page", defaultValue = PAGE_DEFAULT) Integer page,
			@RequestParam(value = "size", defaultValue = SIZE_DEFAULT) Integer size) {
		List<Voto> list = service.findByCodPauta(codPauta, PageRequest.of(page, size));
		List<VotoDTO> listDTO = list.stream().map(x -> mapper.map(x, VotoDTO.class)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	public ResponseEntity<List<ApuracaoDTO>> apuracao(
			@ApiParam(value = "codPauta", required = true) @Valid @PathVariable Long codPauta) {
		List<ApuracaoDTO> apuracao = service.apuracao(codPauta);
		return ResponseEntity.ok().body(apuracao);
	}

}
