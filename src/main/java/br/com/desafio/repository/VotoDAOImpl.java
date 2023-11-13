package br.com.desafio.repository;

import br.com.desafio.domain.entity.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class VotoDAOImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private IvotoDAO ivotoDAO;

    @Transactional()
    public Voto create(Voto voto) { return ivotoDAO.save(voto); }

    @Transactional(readOnly = true)
    public Optional<Voto> findByCodPautaAndCpf(Long codPauta, String cpf) { return ivotoDAO.findByCodPautaAndCpf(codPauta, cpf);}

    @Transactional(readOnly = true)
    public List<Voto> findByCodPauta(Long codPauta, Pageable pageable) {
        return ivotoDAO.findByCodPauta(codPauta, pageable);
    }

//    @Transactional(readOnly = true)
//    public ApuracaoDTO apuracao(Long codPauta) {
//        return ivotoDAO.apuracao(codPauta);
//    }
//@Query(value = " SELECT cod_pauta, " +
//        //"(SELECT COUNT(voto)  FROM voto WHERE cod_pauta = :codPauta AND voto = 'S') , " +
//        "COUNT(voto) FROM voto WHERE cod_pauta = :codPauta ")
//@Param("codPauta")
//    public ApuracaoDTO apuracao(Long codPauta) {
//        ApuracaoDTO apuracaoDTO = new ApuracaoDTO();
//        return apuracaoDTO; //ivotoDAO.apuracao(codPauta);
//    };
}