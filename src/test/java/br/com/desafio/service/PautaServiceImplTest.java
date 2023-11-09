package br.com.desafio.service;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.PautaDTO;
import br.com.desafio.repository.PautaDAOImpl;
import br.com.desafio.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

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
class PautaServiceImplTest {
    public static final Long ID                         = 1L;
    public static final String NAME                     = "Pauta n";
    public static final LocalDateTime DATEINI           = LocalDateTime.parse("2023-11-08T09:00");
    public static final LocalDateTime DATEFIM           = LocalDateTime.parse("2023-11-08T18:00");
    public static final String OBJETO_NAO_ENCONTRADO    = "Objeto não encontrado!";
    public static final int INDEX = 0;
    public static final int PAGE = 0;
    public static final int SIZE = 1;

    @InjectMocks
    private PautaServiceImpl service;
    @Mock
    private PautaDAOImpl dao;
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
    void whenFindByIdThenReturnAnObjectInstance() {
        when(dao.findById(anyLong())).thenReturn(pauta);
        Pauta response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Pauta.class, response.getClass());
        assertEquals(ID, response.getCodPauta());
        assertEquals(NAME, response.getNomPauta());
        assertEquals(DATEINI, response.getDtIniVotacao());
        assertEquals(DATEFIM, response.getDtFimVotacao());
    }

    @Test
    void whenFindPautaByIdThenReturnAnObjectNotFoundException() {
        when(dao.findById(anyLong())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        };
    }

    @Test
    void whenFindAllThenReturnAnListOfPautas() {
        when(dao.findAll(any())).thenReturn(Collections.unmodifiableList(pautaLista));

        List<Pauta>  response = service.findAll(PageRequest.of(PAGE, SIZE));

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Pauta.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getCodPauta());
        assertEquals(NAME, response.get(INDEX).getNomPauta());
        assertEquals(DATEINI, response.get(INDEX).getDtIniVotacao());
        assertEquals(DATEFIM, response.get(INDEX).getDtFimVotacao());
    }

    @Test
    void whenFindAllThenReturnAnObjectNotFoundException() {
        when(dao.findAll(any())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            service.findAll(PageRequest.of(PAGE, SIZE));
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        };
    }

    @Test
    void whenFindByDtIniVotacaoIsNullThenReturnAnListOfPautas() {
        when(dao.findByDtIniVotacaoIsNull(any())).thenReturn(Collections.unmodifiableList(pautaLista));

        List<Pauta>  response = service.findByDtIniVotacaoIsNull(PageRequest.of(PAGE, SIZE));

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Pauta.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getCodPauta());
        assertEquals(NAME, response.get(INDEX).getNomPauta());
        assertEquals(DATEINI, response.get(INDEX).getDtIniVotacao());
        assertEquals(DATEFIM, response.get(INDEX).getDtFimVotacao());
    }

    @Test
    void whenFindByDtIniVotacaoIsNullThenReturnAnObjectNotFoundException() {
        when(dao.findAll(any())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            service.findByDtIniVotacaoIsNull(PageRequest.of(PAGE, SIZE));
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        };
    }

    @Test
    void whenFindByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNullThenReturnAnListOfPautas() {
        when(dao.findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(any())).thenReturn(Collections.unmodifiableList(pautaLista));

        List<Pauta>  response = service.findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(PageRequest.of(PAGE, SIZE));

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Pauta.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getCodPauta());
        assertEquals(NAME, response.get(INDEX).getNomPauta());
        assertEquals(DATEINI, response.get(INDEX).getDtIniVotacao());
        assertEquals(DATEFIM, response.get(INDEX).getDtFimVotacao());
    }

    @Test
    void whenFindByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNullThenReturnAnObjectNotFoundException() {
        when(dao.findAll(any())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            service.findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(PageRequest.of(PAGE, SIZE));
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        };
    }

    @Test
    void whenFindByDtFimVotacaoIsNotNullThenReturnAnListOfPautas() {
        when(dao.findByDtFimVotacaoIsNotNull(any())).thenReturn(Collections.unmodifiableList(pautaLista));

        List<Pauta>  response = service.findByDtFimVotacaoIsNotNull(PageRequest.of(PAGE, SIZE));

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Pauta.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getCodPauta());
        assertEquals(NAME, response.get(INDEX).getNomPauta());
        assertEquals(DATEINI, response.get(INDEX).getDtIniVotacao());
        assertEquals(DATEFIM, response.get(INDEX).getDtFimVotacao());
    }

    @Test
    void whenFindByDtFimVotacaoIsNotNullThenReturnAnObjectNotFoundException() {
        when(dao.findByDtFimVotacaoIsNotNull(any())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try {
            service.findByDtFimVotacaoIsNotNull(PageRequest.of(PAGE, SIZE));
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        };
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(dao.create(any())).thenReturn(pauta);
        Pauta response = dao.create(pauta);
        assertNotNull(response);
        assertEquals(Pauta.class, response.getClass());
        assertEquals(ID, response.getCodPauta());
        assertEquals(NAME, response.getNomPauta());
        assertEquals(DATEINI, response.getDtIniVotacao());
        assertEquals(DATEFIM, response.getDtFimVotacao());
    }

    private void startPauta() {
        pauta = new Pauta(ID, NAME, DATEINI, DATEFIM);
        pautaLista.add(pauta);
        pautaDTO = new PautaDTO(ID, NAME, DATEINI, DATEFIM);
        optionalPauta = Optional.of(new Pauta(ID, NAME, DATEINI, DATEFIM));
    }

}