package br.com.desafio.config;

import br.com.desafio.repository.PautaDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private PautaDAOImpl pautaDAO;
    @Bean
    public void starDB() {
//        RqPautaAdd pauta1 = new RqPautaAdd("Pauta p1");
//        RqPautaAdd pauta2 = new RqPautaAdd("Pauta p2");
//        RqPautaAdd pauta3 = new RqPautaAdd("Pauta p3");
//        RqPautaAdd pauta4 = new RqPautaAdd("Pauta p4");
//
//        pautaDAO.addPauta(pauta1);
//        pautaDAO.addPauta(pauta2);
//        pautaDAO.addPauta(pauta3);
//        pautaDAO.addPauta(pauta4);

    }
}
