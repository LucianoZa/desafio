package br.com.desafio.service;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.RqPautaAdd;
import br.com.desafio.model.RqPautaGet;
import br.com.desafio.model.RsPautaAdd;
import br.com.desafio.repository.PautaDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaServiceImpl implements IpautaService{

    @Autowired
    PautaDAOImpl dao;
    @Override
    public RsPautaAdd addPauta(RqPautaAdd rqPautaAdd) {
        return dao.addPauta(rqPautaAdd);
    }

    @Override
    public List<Pauta> getPauta(String codPauta, RqPautaGet rqPautaGet, Pageable pageable) {
        return dao.getPauta(codPauta, rqPautaGet, pageable);
    }
}
