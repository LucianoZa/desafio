package br.com.desafio.controller;

import br.com.desafio.model.PautaDTO;
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
@RequestMapping(value = "/v1/pauta")
public interface IpautaController {

	Logger log = LoggerFactory.getLogger(IpautaController.class);

	default Optional<ObjectMapper> getObjectMapper() {
		return Optional.empty();
	}

	default Optional<HttpServletRequest> getRequest() {
		return Optional.empty();
	}

	default Optional<String> getAcceptHeader() {
		return getRequest().map(r -> r.getHeader("Accept"));
	}

	@ApiOperation(value = "Cadastra Pauta", nickname = "create", notes = "Cadastra Pauta", tags = { "Pauta", })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created") })
	@RequestMapping( consumes = {
			"application/json" }, method = RequestMethod.POST)
	default ResponseEntity<PautaDTO> create(@RequestBody PautaDTO obj) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
		} else {
			log.warn(
					"ObjectMapper or HttpServletRequest not configured in default Desafio interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@ApiOperation(value = "Consulta Pauta por ID", nickname = "findByID", notes = "Consulta Pauta por ID", tags = { "Pauta", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PautaDTO.class),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/findById/{codPauta}", produces = {
			"application/json" }, method = RequestMethod.GET)
	default ResponseEntity<PautaDTO> findById(
			@ApiParam(value = "codPauta", required = true) @Valid @PathVariable Long codPauta)
	{
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
		} else {
			log.warn(
					"ObjectMapper or HttpServletRequest not configured in default Desafio interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@ApiOperation(value = "Consulta Pauta não Iniciada", nickname = "findByDtIniVotacaoIsNull", notes = "Consulta Pauta não Iniciada", tags = { "Pauta", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PautaDTO.class, responseContainer = "List"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/findByDtIniVotacaoIsNull", produces = {
			"application/json" }, method = RequestMethod.GET)
	default ResponseEntity<List<PautaDTO>> findByDtIniVotacaoIsNull(
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

	@ApiOperation(value = "Consulta Pauta em Andamento", nickname = "findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull", notes = "Consulta Pauta em Andamento", tags = { "Pauta", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PautaDTO.class, responseContainer = "List"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull", produces = {
			"application/json" }, method = RequestMethod.GET)
	default ResponseEntity<List<PautaDTO>> findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(
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

	@ApiOperation(value = "Consulta Pauta Finalizada", nickname = "findByDtFimVotacaoIsNotNull", notes = "Consulta Pauta Finalizada", tags = { "Pauta", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PautaDTO.class, responseContainer = "List"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/findByDtFimVotacaoIsNotNull", produces = {
			"application/json" }, method = RequestMethod.GET)
	default ResponseEntity<List<PautaDTO>> findByDtFimVotacaoIsNotNull(
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

	@ApiOperation(value = "Consulta Todas as Pautas", nickname = "findByDtFimVotacaoIsNotNull", notes = "Consulta Todas as Pautas", tags = { "Pauta", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PautaDTO.class, responseContainer = "List"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/findAll", produces = {
			"application/json" }, method = RequestMethod.GET)
	default ResponseEntity<List<PautaDTO>> findAll(
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

}
