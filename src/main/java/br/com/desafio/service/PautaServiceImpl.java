package br.com.desafio.service;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.PautaDTO;
import br.com.desafio.model.RqPautaAdd;
import br.com.desafio.model.RqPautaGet;
import br.com.desafio.model.RsPautaAdd;
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

    @Autowired
    private PautaDAOImpl dao;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Pauta> getPauta(String codPauta, RqPautaGet rqPautaGet, Pageable pageable) {
        Optional<List<Pauta>> obj = Optional.ofNullable(dao.getPauta(codPauta, rqPautaGet, pageable));
        return obj.orElseThrow(() ->new ObjectNotFoundException("Pauta não encontrada!"));
    }

    public List<Pauta> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    public Pauta create(PautaDTO obj) {
        return dao.create(mapper.map(obj, Pauta.class));
    }
}
