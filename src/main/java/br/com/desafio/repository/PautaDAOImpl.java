package br.com.desafio.repository;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.RqPautaAdd;
import br.com.desafio.model.RqPautaGet;
import br.com.desafio.model.RsPautaAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PautaDAOImpl{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private IpautaDAO ipautaDAO;

    @Transactional(readOnly = true)
    public List<Pauta> getPauta(String codPauta, RqPautaGet rqPautaGet, Pageable pageable){

        List<Pauta> response = new ArrayList<>();
        switch(rqPautaGet.getStatusPauta()) {
            case "N": //Sessão de votação não iniciada
                response = ipautaDAO.findByDtIniVotacaoIsNull(pageable);
                break;
            case "A": //Sessão de votação iniciada, mas não finalizada
                response = ipautaDAO.findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(pageable);
                break;
            case "F": //Sessão de votação finalizada
                response = ipautaDAO.findByDtFimVotacaoIsNotNull(pageable); //
                break;
            default: //Todas as Pautas cadastradas
                response = ipautaDAO.findAll(pageable).toList();
        }
        return response;
    }

    @Transactional
    public RsPautaAdd addPauta(RqPautaAdd rqPautaAdd) {

        Pauta pauta = new Pauta(null, rqPautaAdd.getNomPauta(), null, null);
        pauta = ipautaDAO.save(pauta);

        RsPautaAdd rsPautaAdd = new RsPautaAdd();
        rsPautaAdd.setCodPauta(pauta.getCodPauta());
        rsPautaAdd.setNomPauta(pauta.getNomPauta());

        return rsPautaAdd;
    }

}