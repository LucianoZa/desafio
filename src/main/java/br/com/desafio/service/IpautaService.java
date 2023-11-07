package br.com.desafio.service;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.RqPautaAdd;
import br.com.desafio.model.RqPautaGet;
import br.com.desafio.model.RsPautaAdd;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IpautaService {

     public RsPautaAdd addPauta(RqPautaAdd rqPautaAdd);
     public List<Pauta> getPauta(String codPauta, RqPautaGet rqPautaGet, Pageable pageable);

}
