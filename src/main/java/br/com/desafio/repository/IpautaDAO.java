package br.com.desafio.repository;

import br.com.desafio.domain.entity.Pauta;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IpautaDAO extends JpaRepository<Pauta, Long> {
    Optional<Pauta> findByNomPauta(String nomPauta);
    List<Pauta> findByDtIniVotacaoIsNull(Pageable pageable);
    List<Pauta> findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(Pageable pageable);
    List<Pauta> findByDtFimVotacaoIsNotNull(Pageable pageable);

}
