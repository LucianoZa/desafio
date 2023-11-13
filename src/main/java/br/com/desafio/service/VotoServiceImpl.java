package br.com.desafio.service;

import br.com.desafio.domain.entity.Voto;
import br.com.desafio.model.VotoDTO;
import br.com.desafio.repository.VotoDAOImpl;
import br.com.desafio.service.exceptions.DataIntegrityViolationException;
import br.com.desafio.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotoServiceImpl implements IvotoService{

    public static final String VOTO_NAO_ENCONTRADO = "Votação não encontrada";
    @Autowired
    private VotoDAOImpl dao;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Voto create(VotoDTO obj) {
        findByCodPautaAndCpf(obj);
        return dao.create(mapper.map(obj, Voto.class));
    }

    public void findByCodPautaAndCpf(VotoDTO obj) {
        Optional<Voto> voto = dao.findByCodPautaAndCpf(obj.getCodPauta(), obj.getCpf());
        if (voto.isPresent()) {
            throw new DataIntegrityViolationException("Voto já Registrado");
        }
    }

    @Override
    public List<Voto> findByCodPauta(Long codPauta, Pageable pageable) {
        Optional<List<Voto>> obj = Optional.ofNullable(dao.findByCodPauta(codPauta, pageable));
        return obj.orElseThrow(() ->new ObjectNotFoundException(VOTO_NAO_ENCONTRADO));
    }

//    @Override
//    public ApuracaoDTO apuracao(Long codPauta) {
//        return dao.apuracao(codPauta);
//    }

}