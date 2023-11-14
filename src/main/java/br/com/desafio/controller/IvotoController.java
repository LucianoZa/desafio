package br.com.desafio.controller;

import br.com.desafio.model.ApuracaoDTO;
import br.com.desafio.model.VotoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(value = "Desafio", description = "Sessao de votacao API")
@RequestMapping(value = "/v1/pauta/{codPauta}")
public interface IvotoController {

	Logger log = LoggerFactory.getLogger(IvotoController.class);

	default Optional<ObjectMapper> getObjectMapper() {
		return Optional.empty();
	}

	default Optional<HttpServletRequest> getRequest() {
		return Optional.empty();
	}

	default Optional<String> getAcceptHeader() {
		return getRequest().map(r -> r.getHeader("Accept"));
	}

	@ApiOperation(value = "Cadastrar Voto", nickname = "create", notes = "Cadastra Voto", tags = { "Votação", })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created") })
	@RequestMapping( consumes = {
			"application/json" }, method = RequestMethod.POST)
	default ResponseEntity<VotoDTO> create(@RequestBody VotoDTO obj, @ApiParam(value = "codPauta", required = true) @Valid @PathVariable Long codPauta) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
		} else {
			log.warn(
					"ObjectMapper or HttpServletRequest not configured in default Desafio interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@ApiOperation(value = "Consultar votos da sessão", nickname = "findByCodPauta", notes = "Consulta votos da sesssão", tags = { "Votação", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = VotoDTO.class, responseContainer = "List"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/findByCodPauta", produces = {
			"application/json" }, method = RequestMethod.GET)
	default ResponseEntity<List<VotoDTO>> findByCodPauta(
			@ApiParam(value = "codPauta", required = true) @Valid @PathVariable Long codPauta,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size)
	{
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
		} else {
			log.warn(
					"ObjectMapper or HttpServletRequest not configured in default Desafio interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@ApiOperation(value = "Consultar Apuração da sessão", nickname = "apuracao", notes = "Consulta resultado final de uma votação", tags = { "Votação", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ApuracaoDTO.class, responseContainer = "List"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/apuracao", produces = {
			"application/json" }, method = RequestMethod.GET)
	default ResponseEntity<List<ApuracaoDTO>> apuracao(
			@ApiParam(value = "codPauta", required = true) @Valid @PathVariable Long codPauta)
	{
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
		} else {
			log.warn(
					"ObjectMapper or HttpServletRequest not configured in default Desafio interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}
}