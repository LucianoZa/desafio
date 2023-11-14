package br.com.desafio.repository;

import br.com.desafio.domain.entity.Voto;
import br.com.desafio.model.ApuracaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
    public Optional<Voto> findByCodPautaAndCpf(Long codPauta, String cpf) { return ivotoDAO.findByCodPautaAndCpf(codPauta, cpf);}

    @Transactional(readOnly = true)
    public List<Voto> findByCodPauta(Long codPauta, Pageable pageable) {
        return ivotoDAO.findByCodPauta(codPauta, pageable);
    }

    public List<ApuracaoDTO> GetApuracao(Long codPauta) {
        return ivotoDAO.GetApuracao(codPauta);
    }

}