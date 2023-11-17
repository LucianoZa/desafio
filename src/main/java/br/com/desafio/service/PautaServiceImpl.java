package br.com.desafio.service;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.PautaDTO;
import br.com.desafio.repository.PautaDAOImpl;
import br.com.desafio.service.exceptions.DataIntegrityViolationException;
import br.com.desafio.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PautaServiceImpl implements IpautaService{

    public static final String PAUTA_NAO_ENCONTRADA = "Pauta não encontrada";
    public static final String NOME_PAUTA_JA_CADASTRADO  = "Nome da pauta já cadastrado";

    @Autowired
    private PautaDAOImpl dao;

    @Autowired
    private ModelMapper mapper;

    public Pauta create(PautaDTO obj) {
        findByNomPauta(obj);
        return dao.create(mapper.map(obj, Pauta.class));
    }

    @Override
    public Pauta update(PautaDTO obj) {
        findByNomPauta(obj);
        return dao.update(mapper.map(obj, Pauta.class));
    }

    @Override
    public void delete(Long id) {
        findById(id);
        dao.deleteById(id);
    }

    @Override
    public void findByNomPauta(PautaDTO obj) {
        Optional<Pauta> pauta = dao.findByNomPauta(obj.getNomPauta());
        if (pauta.isPresent() && !pauta.get().getId().equals(obj.getId())) {
            throw new DataIntegrityViolationException(NOME_PAUTA_JA_CADASTRADO);
        }
    }

    @Override
    public Pauta findById(Long id) {
        Optional<Pauta> obj = dao.findById(id);
        return obj.orElseThrow(() ->new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
    }

    @Override
    public List<Pauta> findByDtIniVotacaoIsNull(Pageable pageable) {
        Optional<List<Pauta>> obj = Optional.ofNullable(dao.findByDtIniVotacaoIsNull(pageable));
        return obj.orElseThrow(() ->new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
    }

    @Override
    public List<Pauta> findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(Pageable pageable) {
        Optional<List<Pauta>> obj = Optional.ofNullable(dao.findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(pageable));
        return obj.orElseThrow(() ->new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
    }
    @Override
    public List<Pauta> findByDtFimVotacaoIsNotNull(Pageable pageable) {
        Optional<List<Pauta>> obj = Optional.ofNullable(dao.findByDtFimVotacaoIsNotNull(pageable));
        return obj.orElseThrow(() ->new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
    }

    @Override
    public List<Pauta> findAll(Pageable pageable) {
        Optional<List<Pauta>> obj = Optional.ofNullable(dao.findAll(pageable));
        return obj.orElseThrow(() ->new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
    }

}