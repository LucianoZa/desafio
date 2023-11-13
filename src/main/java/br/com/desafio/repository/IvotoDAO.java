package br.com.desafio.repository;

import br.com.desafio.domain.entity.Voto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IvotoDAO extends JpaRepository<Voto, Long> {
    Optional<Voto> findByCodPautaAndCpf(Long codPauta, String cpf);
    List<Voto> findByCodPauta(Long codPauta, Pageable pageable);

//    @Query(value = " SELECT cod_pauta, " +
//                            "(SELECT COUNT(voto)  FROM voto WHERE cod_pauta = :codPauta AND voto = 'S') , " +
//                            "COUNT(voto) FROM voto WHERE cod_pauta = :codPauta ")

//    @Query(value = " SELECT cod_pauta, 2, " +
//        //"(SELECT COUNT(voto)  FROM voto WHERE cod_pauta = :codPauta AND voto = 'S') , " +
//        "COUNT(voto) FROM voto Limit 1, nativeQuery = true ") //WHERE cod_pauta = :codPauta ")
//    ApuracaoDTO apuracao(Long codPauta);

}
