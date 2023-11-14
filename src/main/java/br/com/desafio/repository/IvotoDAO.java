package br.com.desafio.repository;

import br.com.desafio.domain.entity.Voto;
import br.com.desafio.model.ApuracaoDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IvotoDAO extends JpaRepository<Voto, Long> {

    //Spring Data com Query methods
    Optional<Voto> findByCodPautaAndCpf(Long codPauta, String cpf);
    List<Voto> findByCodPauta(Long codPauta, Pageable pageable);

    //Spring Data com @Query e @NamedNativeQuery @SqlResultSetMapping na entidade Voto
    @Query(nativeQuery = true)
    List<ApuracaoDTO> GetApuracao(Long codPauta);


}
