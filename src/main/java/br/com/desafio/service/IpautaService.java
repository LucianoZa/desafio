package br.com.desafio.service;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.PautaDTO;
import br.com.desafio.model.RqPautaGet;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IpautaService {

     List<Pauta> getPauta(String codPauta, RqPautaGet rqPautaGet, Pageable pageable);

     List<Pauta> findAll( Pageable pageable);
     Pauta create(PautaDTO obj);

}
