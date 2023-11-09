package br.com.desafio.repository;

import br.com.desafio.domain.entity.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PautaDAOImpl{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private IpautaDAO ipautaDAO;

    @Transactional()
    public Pauta create(Pauta pauta) { return ipautaDAO.save(pauta); }

    @Transactional(readOnly = true)
    public Pauta findById(Long codPauta) { return ipautaDAO.findById(codPauta).get();}

    @Transactional(readOnly = true)
    public List<Pauta> findByDtIniVotacaoIsNull(Pageable pageable) { return ipautaDAO.findByDtIniVotacaoIsNull(pageable); }

    @Transactional(readOnly = true)
    public List<Pauta> findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(Pageable pageable) { return ipautaDAO.findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(pageable); }

    @Transactional(readOnly = true)
    public List<Pauta> findByDtFimVotacaoIsNotNull(Pageable pageable) { return ipautaDAO.findByDtFimVotacaoIsNotNull(pageable); }

    @Transactional(readOnly = true)
    public List<Pauta> findAll(Pageable pageable) {
        return ipautaDAO.findAll(pageable).toList();
    }

}