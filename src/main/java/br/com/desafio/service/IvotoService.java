package br.com.desafio.service;

import br.com.desafio.domain.entity.Voto;
import br.com.desafio.model.VotoDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IvotoService {

     Voto create(VotoDTO obj);
     List<Voto> findByCodPauta(Long codPauta, Pageable pageable);
//     ApuracaoDTO apuracao(Long codPauta);
}
