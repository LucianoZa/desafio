package br.com.desafio.service;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.model.PautaDTO;
import br.com.desafio.repository.PautaDAOImpl;
import br.com.desafio.service.exceptions.DataIntegrityViolationException;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class PautaServiceImplTest {
    public static final Long ID                         = 1L;
    public static final String NAME                     = "Pauta n";
    public static final LocalDateTime DATEINI           = LocalDateTime.parse("2022-12-08T09:00");
    public static final LocalDateTime DATEFIM           = LocalDateTime.parse("2024-12-08T18:00");

    public static final int INDEX = 0;
    public static final int PAGE = 0;
    public static final int SIZE = 1;
    public static final String PAUTA_NAO_ENCONTRADA = "Pauta não encontrada";
    public static final String NOME_PAUTA_JA_CADASTRADO = "Nome da pauta já cadastrado";

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
        startPauta();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(dao.create(any())).thenReturn(pauta);

        Pauta response = service.create(pautaDTO);

        assertNotNull(response);
        assertEquals(Pauta.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getNomPauta());
        assertEquals(DATEINI, response.getDtIniVotacao());
        assertEquals(DATEFIM, response.getDtFimVotacao());
    }

    @Test
    void whenCreateThenAnDataIntegrityViolationException() {
        when(dao.findByNomPauta(anyString())).thenReturn(optionalPauta);

        try{
            optionalPauta.get().setId(2L);
            service.create(pautaDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(NOME_PAUTA_JA_CADASTRADO, ex.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnAnObjectPautaDTO(){
        when(dao.update(pauta)).thenReturn(pauta);
        when(mapper.map(any(), any())).thenReturn(pauta);

        Pauta response = service.update(pautaDTO);

        assertNotNull(response);
        assertEquals(Pauta.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getNomPauta());
        assertEquals(DATEINI, response.getDtIniVotacao());
        assertEquals(DATEFIM, response.getDtFimVotacao());
    }

    @Test
    void whenUpdateThenAnDataIntegrityViolationException() {
        when(dao.findByNomPauta(anyString())).thenReturn(optionalPauta);

        try{
            optionalPauta.get().setId(2L);
            service.update(pautaDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(NOME_PAUTA_JA_CADASTRADO, ex.getMessage());
        }
    }

    @Test
    void whenDeleteWithSuccess() {
        when(dao.findById(anyLong())).thenReturn(optionalPauta);
        doNothing().when(dao).deleteById(anyLong());
        service.delete(ID);
    }

    @Test
    void deleteWithObjectNotFoundException() {
        when(dao.findById(anyLong())).thenThrow(new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));

        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(PAUTA_NAO_ENCONTRADA, ex.getMessage());

        }
    }

    @Test
    void whenFindByIdThenReturnAnObjectInstance() {
        when(dao.findById(anyLong())).thenReturn(Optional.ofNullable(pauta));
        Pauta response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Pauta.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getNomPauta());
        assertEquals(DATEINI, response.getDtIniVotacao());
        assertEquals(DATEFIM, response.getDtFimVotacao());
    }

    @Test
    void whenFindPautaByIdThenReturnAnObjectNotFoundException() {
        when(dao.findById(anyLong())).thenThrow(new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(PAUTA_NAO_ENCONTRADA, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfPautas() {
        when(dao.findAll(any())).thenReturn(Collections.unmodifiableList(pautaLista));

        List<Pauta>  response = service.findAll(PageRequest.of(PAGE, SIZE));

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Pauta.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getNomPauta());
        assertEquals(DATEINI, response.get(INDEX).getDtIniVotacao());
        assertEquals(DATEFIM, response.get(INDEX).getDtFimVotacao());
    }

    @Test
    void whenFindAllThenReturnAnObjectNotFoundException() {
        when(dao.findAll(any())).thenThrow(new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
        try {
            service.findAll(PageRequest.of(PAGE, SIZE));
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(PAUTA_NAO_ENCONTRADA, ex.getMessage());
        }
    }

    @Test
    void whenFindByDtIniVotacaoIsNullThenReturnAnListOfPautas() {
        when(dao.findByDtIniVotacaoIsNull(any())).thenReturn(Collections.unmodifiableList(pautaLista));

        List<Pauta>  response = service.findByDtIniVotacaoIsNull(PageRequest.of(PAGE, SIZE));

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Pauta.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getNomPauta());
        assertEquals(DATEINI, response.get(INDEX).getDtIniVotacao());
        assertEquals(DATEFIM, response.get(INDEX).getDtFimVotacao());
    }

    @Test
    void whenFindByDtIniVotacaoIsNullThenReturnAnObjectNotFoundException() {
        when(dao.findAll(any())).thenThrow(new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
        try {
            service.findByDtIniVotacaoIsNull(PageRequest.of(PAGE, SIZE));
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(PAUTA_NAO_ENCONTRADA, ex.getMessage());
        }
    }

    @Test
    void whenFindByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNullThenReturnAnListOfPautas() {
        when(dao.findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(any())).thenReturn(Collections.unmodifiableList(pautaLista));

        List<Pauta>  response = service.findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(PageRequest.of(PAGE, SIZE));

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Pauta.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getNomPauta());
        assertEquals(DATEINI, response.get(INDEX).getDtIniVotacao());
        assertEquals(DATEFIM, response.get(INDEX).getDtFimVotacao());
    }

    @Test
    void whenFindByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNullThenReturnAnObjectNotFoundException() {
        when(dao.findAll(any())).thenThrow(new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
        try {
            service.findByDtIniVotacaoIsNotNullAndDtFimVotacaoIsNull(PageRequest.of(PAGE, SIZE));
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(PAUTA_NAO_ENCONTRADA, ex.getMessage());
        }
    }

    @Test
    void whenFindByDtFimVotacaoIsNotNullThenReturnAnListOfPautas() {
        when(dao.findByDtFimVotacaoIsNotNull(any())).thenReturn(Collections.unmodifiableList(pautaLista));

        List<Pauta>  response = service.findByDtFimVotacaoIsNotNull(PageRequest.of(PAGE, SIZE));

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Pauta.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getNomPauta());
        assertEquals(DATEINI, response.get(INDEX).getDtIniVotacao());
        assertEquals(DATEFIM, response.get(INDEX).getDtFimVotacao());
    }

    @Test
    void whenFindByDtFimVotacaoIsNotNullThenReturnAnObjectNotFoundException() {
        when(dao.findByDtFimVotacaoIsNotNull(any())).thenThrow(new ObjectNotFoundException(PAUTA_NAO_ENCONTRADA));
        try {
            service.findByDtFimVotacaoIsNotNull(PageRequest.of(PAGE, SIZE));
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(PAUTA_NAO_ENCONTRADA, ex.getMessage());
        }
    }

    private void startPauta() {
        pauta = new Pauta(ID, NAME, DATEINI, DATEFIM);
        pautaLista.add(pauta);
        pautaDTO = new PautaDTO(ID, NAME, DATEINI, DATEFIM);
        optionalPauta = Optional.of(new Pauta(ID, NAME, DATEINI, DATEFIM));
    }
}