package br.com.desafio.repository;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.RqPautaAdd;
import br.com.desafio.model.RqPautaGet;
import br.com.desafio.model.RsPautaAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PautaDAOImpl implements IpautaDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private IpautaRepository iPautaRepository;

    @Transactional(readOnly = true)
    public List<Pauta> getPauta(String codPauta, RqPautaGet rqPautaGet, Pageable pageable){

        List<Pauta> response = new ArrayList<>();

        switch(rqPautaGet.getStatusPauta()) {
            case "N": //Sessão de votação não iniciada
                //response = entityManager.createQuery(" SELECT P FROM Pauta P WHERE DT_INI_VOTACAO = NULL ", Pauta.class).getResultList();
                response = iPautaRepository.findByDtIniVotacaoIsNull(pageable);
                break;
            case "A": //Sessão de votação iniciada, mas não finalizada
                //response = entityManager.createQuery(" SELECT P FROM Pauta P WHERE DT_INI_VOTACAO != NULL AND DT_FIM_VOTACAO = NULL ", Pauta.class).getResultList();
                response = iPautaRepository.findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(pageable);
                break;
            case "F": //Sessão de votação finalizada
                //response = entityManager.createQuery(" SELECT P FROM Pauta P WHERE DT_FIM_VOTACAO != NULL ", Pauta.class).getResultList();
                response = iPautaRepository.findByDtFimVotacaoIsNotNull(pageable); //
                break;
            default: //Todas as Pautas cadastradas
                //response = entityManager.createQuery(" FROM Pauta", Pauta.class).getResultList();
                response = iPautaRepository.findAll(pageable).toList();
        }
        return response;
    }

    @Transactional
    public RsPautaAdd addPauta(RqPautaAdd rqPautaAdd) {

        long primaryKey = addAndGetPrimaryKey(rqPautaAdd);

        RsPautaAdd rsPautaAdd = new RsPautaAdd();
        rsPautaAdd.setCodPauta(primaryKey);
        rsPautaAdd.setNomPauta(rqPautaAdd.getNomPauta());

        return rsPautaAdd;
    }

    private long addAndGetPrimaryKey(RqPautaAdd rqPautaAdd) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement("INSERT INTO PAUTA (NOM_PAUTA) VALUES (?) ", Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, rqPautaAdd.getNomPauta());

                return statement;
            }
        }, holder);

        long primaryKey = holder.getKey().longValue();
        return primaryKey;
    }


}
