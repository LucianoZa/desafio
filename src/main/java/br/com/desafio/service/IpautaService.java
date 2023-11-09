package br.com.desafio.service;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.PautaDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IpautaService {

     Pauta create(PautaDTO obj);
     Pauta findById(Long codPauta);
     List<Pauta> findByDtIniVotacaoIsNull( Pageable pageable);
     List<Pauta> findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull( Pageable pageable);
     List<Pauta> findByDtFimVotacaoIsNotNull( Pageable pageable);
     List<Pauta> findAll( Pageable pageable);

}
