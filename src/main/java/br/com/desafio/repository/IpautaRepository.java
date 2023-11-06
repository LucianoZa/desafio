package br.com.desafio.repository;

import br.com.desafio.domain.entity.Pauta;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IpautaRepository extends JpaRepository<Pauta, Long> {
    List<Pauta> findByDtFimVotacaoIsNotNull(Pageable pageable);
    List<Pauta> findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(Pageable pageable);
    List<Pauta> findByDtIniVotacaoIsNull(Pageable pageable);
}
