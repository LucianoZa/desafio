package br.com.desafio.controller;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.PautaDTO;
import br.com.desafio.model.RqPautaGet;
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
//@RequestMapping(value = "/v1")
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
	@RequestMapping(value = "/pauta", consumes = {
			"application/json" }, method = RequestMethod.POST)
	default ResponseEntity<PautaDTO> create(@RequestBody PautaDTO obj) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
		} else {
			log.warn(
					"ObjectMapper or HttpServletRequest not configured in default Desafio interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@ApiOperation(value = "Consulta Pauta", nickname = "getPauta", notes = "Consulta Pauta", tags = { "Pauta", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Pauta.class, responseContainer = "List"),
							@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = "/pauta/{codPauta}", produces = {
			"application/json" }, method = RequestMethod.GET)
	default ResponseEntity<List<Pauta>> getPauta(
			@ApiParam(value = "CodPauta", required = true) @Valid @PathVariable String codPauta,
			@ApiParam(value = "PautaRequest", required = true) @Valid @RequestBody RqPautaGet rqPautaGet,
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
