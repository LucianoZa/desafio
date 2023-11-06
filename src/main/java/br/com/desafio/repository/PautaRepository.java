package br.com.desafio.repository;

import br.com.desafio.domain.entity.Pauta;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class PautaRepository {

    @Autowired
    private IpautaRepository iPautaRepository;

    public List<Pauta> findAll() {
        return iPautaRepository.findAll();
    }


}
