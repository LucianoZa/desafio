package br.com.desafio.service;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.PautaDTO;
import br.com.desafio.repository.PautaDAOImpl;
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
    @Autowired
    private PautaDAOImpl dao;

    @Autowired
    private ModelMapper mapper;

    public Pauta create(PautaDTO obj) {
        return dao.create(mapper.map(obj, Pauta.class));
    }

    public Pauta findById(Long codPauta) {
        Optional<Pauta> obj = Optional.ofNullable(dao.findById(codPauta));
        return obj.orElseThrow(() ->new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
    }

    public List<Pauta> findByDtIniVotacaoIsNull(Pageable pageable) {
        Optional<List<Pauta>> obj = Optional.ofNullable(dao.findByDtIniVotacaoIsNull(pageable));
        return obj.orElseThrow(() ->new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
    }

    public List<Pauta> findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(Pageable pageable) {
        Optional<List<Pauta>> obj = Optional.ofNullable(dao.findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(pageable));
        return obj.orElseThrow(() ->new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
    }

    public List<Pauta> findByDtFimVotacaoIsNotNull(Pageable pageable) {
        Optional<List<Pauta>> obj = Optional.ofNullable(dao.findByDtFimVotacaoIsNotNull(pageable));
        return obj.orElseThrow(() ->new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
    }

    public List<Pauta> findAll(Pageable pageable) {
        Optional<List<Pauta>> obj = Optional.ofNullable(dao.findAll(pageable));
        return obj.orElseThrow(() ->new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
    }

}