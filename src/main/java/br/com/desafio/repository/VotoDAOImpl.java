package br.com.desafio.repository;

import br.com.desafio.domain.entity.Voto;
import br.com.desafio.model.ApuracaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public class VotoDAOImpl {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private IvotoDAO ivotoDAO;

    @Transactional()
    public Voto create(Voto voto) { return ivotoDAO.save(voto); }

    @Transactional(readOnly = true)
    public Optional<Voto> findByCodPautaAndCpf(BigInteger codPauta, String cpf) { return ivotoDAO.findByCodPautaAndCpf(codPauta, cpf);}

    @Transactional(readOnly = true)
    public List<Voto> findByCodPauta(BigInteger codPauta, Pageable pageable) {
        return ivotoDAO.findByCodPauta(codPauta, pageable);
    }

    @Transactional(readOnly = true)
    public List<ApuracaoDTO> GetApuracao(BigInteger codPauta) {
        return ivotoDAO.GetApuracao(codPauta);
    }

}