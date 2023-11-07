package br.com.desafio.repository;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.RqPautaAdd;
import br.com.desafio.model.RqPautaGet;
import br.com.desafio.model.RsPautaAdd;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IpautaDAO extends JpaRepository<Pauta, Long> {
    List<Pauta> findByDtFimVotacaoIsNotNull(Pageable pageable);
    List<Pauta> findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(Pageable pageable);
    List<Pauta> findByDtIniVotacaoIsNull(Pageable pageable);
}
