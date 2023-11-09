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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class PautaControllerImplTest {

    public static final Long ID                         = 1L;
    public static final String NAME                     = "Pauta n";
    public static final LocalDateTime DATEINI           = LocalDateTime.parse("2023-11-08T09:00");
    public static final LocalDateTime DATEFIM           = LocalDateTime.parse("2023-11-08T18:00");
    public static final int INDEX = 0;
    public static final int PAGE = 0;
    public static final int SIZE = 1;

    @InjectMocks
    private PautaControllerImpl controller;

    @Mock
    private PautaServiceImpl service;

    @Mock
    private ModelMapper mapper;

    private Pauta pauta;
    private PautaDTO pautaDTO;
    private Optional<Pauta> optionalPauta;
    private List<Pauta> pautaLista = new ArrayList<>();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPauta();
    }


    @Test
    void create() {
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyLong())).thenReturn(pauta);
        when(mapper.map(any(), any())).thenReturn(pautaDTO);

        ResponseEntity<PautaDTO> response = controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PautaDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getCodPauta());
        assertEquals(NAME, response.getBody().getNomPauta());
        assertEquals(DATEINI, response.getBody().getDtIniVotacao());
        assertEquals(DATEFIM, response.getBody().getDtFimVotacao());

    }

    @Test
    void whenFindAllThenReturnAListOfPautaDTO() {
        when(service.findAll(any())).thenReturn(Collections.unmodifiableList(pautaLista));
        when(mapper.map(any(), any())).thenReturn(pautaDTO);

        ResponseEntity<List<PautaDTO>> response = controller.findAll(PAGE, SIZE);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(PautaDTO.class, response.getBody().get(INDEX).getClass());
        assertEquals(1, response.getBody().size());

        assertEquals(ID, response.getBody().get(INDEX).getCodPauta());
        assertEquals(NAME, response.getBody().get(INDEX).getNomPauta());
        assertEquals(DATEINI, response.getBody().get(INDEX).getDtIniVotacao());
        assertEquals(DATEFIM, response.getBody().get(INDEX).getDtFimVotacao());
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



    private void startPauta() {
        pauta = new Pauta(ID, NAME, DATEINI, DATEFIM);
        pautaLista.add(pauta);
        pautaDTO = new PautaDTO(ID, NAME, DATEINI, DATEFIM);
        optionalPauta = Optional.of(new Pauta(ID, NAME, DATEINI, DATEFIM));
    }
}