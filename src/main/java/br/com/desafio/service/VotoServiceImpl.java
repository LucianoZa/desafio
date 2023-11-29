package br.com.desafio.service;

import br.com.desafio.domain.entity.Voto;
import br.com.desafio.model.ApuracaoDTO;
import br.com.desafio.model.SessaoDTO;
import br.com.desafio.model.VotoDTO;
import br.com.desafio.repository.PautaDAOImpl;
import br.com.desafio.repository.VotoDAOImpl;
import br.com.desafio.service.exceptions.DataIntegrityViolationException;
import br.com.desafio.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class VotoServiceImpl implements IvotoService{

    public static final String VOTO_NAO_ENCONTRADO = "{voto.nao.encontrado}";

    @Autowired
    private ModelMapper mapper;

    @Autowired
    VotoDAOImpl dao;

    @Autowired
    PautaDAOImpl pautaDao;

    @Override
    public Voto create(VotoDTO obj) {
        findByCodPautaAndCpf(obj);
        return dao.create(mapper.map(obj, Voto.class));
    }

    public void findByCodPautaAndCpf(VotoDTO obj) {
        List<SessaoDTO> pauta = pautaDao.GetSessao(obj.getCodPauta().longValue());
        if (pauta.isEmpty()) throw new DataIntegrityViolationException("{sessao.encerrada.inexistente}");
        Optional<Voto> voto = dao.findByCodPautaAndCpf(obj.getCodPauta(), obj.getCpf());
        if (voto.isPresent()) throw new DataIntegrityViolationException("{voto.ja.cadastrado}");
    }

    @Override
    public List<Voto> findByCodPauta(BigInteger codPauta, Pageable pageable) {
        Optional<List<Voto>> obj = Optional.ofNullable(dao.findByCodPauta(codPauta, pageable));
        return obj.orElseThrow(() ->new ObjectNotFoundException(VOTO_NAO_ENCONTRADO));
    }

    public List<ApuracaoDTO> apuracao(BigInteger codPauta) {return dao.GetApuracao(codPauta);}

}