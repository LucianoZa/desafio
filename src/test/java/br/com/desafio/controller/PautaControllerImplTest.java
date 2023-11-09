package br.com.desafio.controller;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.PautaDTO;
import br.com.desafio.service.PautaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class PautaControllerImplTest {

    public static final Long ID                         = 1L;
    public static final String NAME                     = "Pauta n";
    public static final LocalDateTime DATEINI           = LocalDateTime.parse("2023-11-08T09:00");
    public static final LocalDateTime DATEFIM           = LocalDateTime.parse("2023-11-08T18:00");

    @InjectMocks
    private PautaControllerImpl controller;

    @Mock
    private PautaServiceImpl daoservice;

    @Mock
    private ModelMapper mapper;

    private Pauta pauta;
    private PautaDTO pautaDTO;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByDtIniVotacaoIsNull() {
    }

    @Test
    void findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull() {
    }

    @Test
    void findByDtFimVotacaoIsNotNull() {
    }

    @Test
    void findAll() {
    }

    private void startPauta() {
        pauta = new Pauta(ID, NAME, DATEINI, DATEFIM);
        pautaDTO = new PautaDTO(ID, NAME, DATEINI, DATEFIM);
    }
}