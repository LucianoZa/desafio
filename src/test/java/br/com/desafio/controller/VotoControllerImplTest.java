package br.com.desafio.controller;

import br.com.desafio.domain.entity.Voto;
import br.com.desafio.model.ApuracaoDTO;
import br.com.desafio.model.VotoDTO;
import br.com.desafio.service.VotoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class VotoControllerImplTest {

    public static final BigInteger ID                   = BigInteger.valueOf(1);
    public static final Long CODPAUTA                   = 1L;
    public static final String CPF                      = "12345678901";
    public static final String VOTO                     = "S";
    public static final int INDEX                       = 0;
    public static final int PAGE                        = 0;
    public static final int SIZE                        = 1;

    public static final BigInteger COD_PAUTA            = BigInteger.valueOf(1);
    public static final BigInteger VOTOSTOTALSIM        = BigInteger.valueOf(1);
    public static final BigInteger VOTOSTOTAL           = BigInteger.valueOf(2);

    @InjectMocks
    private VotoControllerImpl controller;

    @Mock
    private VotoServiceImpl service;

    @Mock
    private ModelMapper mapper;

    private Voto voto;
    private VotoDTO votoDTO;
    private Optional<Voto> optionalVoto;
    private List<Voto> votoLista = new ArrayList<>();
    private ApuracaoDTO apuracaoDTO;
    private List<ApuracaoDTO> apuracaoDTOLista = new ArrayList<>();
    private MockHttpServletRequest mockRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockRequest = new MockHttpServletRequest();
        mockRequest.setContextPath("/desafio");
        ServletRequestAttributes attrs = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(attrs);
        startVoto();
    }

    @Test
    void whenCreateThenReturnCreatedAndVotoDTO() {
        when(service.create(any())).thenReturn(voto);

        ResponseEntity<VotoDTO> response = controller.create(votoDTO, COD_PAUTA.toString());

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));

    }

    @Test
    void whenFindByCodPautaThenReturnAListOfVotoDTO() {

        when(service.findByCodPauta(COD_PAUTA, PageRequest.of(PAGE, SIZE))).thenReturn(votoLista);
        when(mapper.map(any(), any())).thenReturn(votoDTO);

        ResponseEntity<List<VotoDTO>> response = controller.findByCodPauta(COD_PAUTA.toString(), PAGE, SIZE);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(VotoDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(COD_PAUTA, response.getBody().get(INDEX).getCodPauta());
        assertEquals(CPF, response.getBody().get(INDEX).getCpf());
        assertEquals(VOTO, response.getBody().get(INDEX).getVoto());

    }

    @Test
    void whenApuracaoThenReturnAListOfApuracao() {

        when(service.apuracao(any())).thenReturn(apuracaoDTOLista);
        when(mapper.map(any(), any())).thenReturn(apuracaoDTO);

        ResponseEntity<List<ApuracaoDTO>> response = controller.apuracao(COD_PAUTA.toString());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(ApuracaoDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(COD_PAUTA, response.getBody().get(INDEX).getCod_Pauta());
        assertEquals(VOTOSTOTALSIM, response.getBody().get(INDEX).getVotosTotalSim());
        assertEquals(VOTOSTOTAL, response.getBody().get(INDEX).getVotosTotal());
    }

    private void startVoto() {
        voto = new Voto(ID, COD_PAUTA, CPF, VOTO);
        votoLista.add(voto);
        votoDTO = new VotoDTO(ID, COD_PAUTA, CPF, VOTO);
        optionalVoto = Optional.of(new Voto(ID, COD_PAUTA, CPF, VOTO));
        apuracaoDTO = new ApuracaoDTO(COD_PAUTA, VOTOSTOTALSIM, VOTOSTOTAL);
        apuracaoDTOLista.add(apuracaoDTO);
    }

}