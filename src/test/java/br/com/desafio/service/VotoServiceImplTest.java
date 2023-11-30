package br.com.desafio.service;

import br.com.desafio.domain.entity.Pauta;
import br.com.desafio.domain.entity.Voto;
import br.com.desafio.model.ApuracaoDTO;
import br.com.desafio.model.SessaoDTO;
import br.com.desafio.model.VotoDTO;
import br.com.desafio.repository.PautaDAOImpl;
import br.com.desafio.repository.VotoDAOImpl;
import br.com.desafio.service.exceptions.DataIntegrityViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class VotoServiceImplTest {

    public static final Long CODPAUTA                   = 1L;
    public static final String CPF                      = "12345678901";
    public static final String VOTO                     = "S";
    public static final int INDEX                       = 0;
    public static final int PAGE                        = 0;
    public static final int SIZE                        = 1;

    public static final BigInteger ID                   = BigInteger.valueOf(1);
    public static final BigInteger COD_PAUTA            = BigInteger.valueOf(1);
    public static final BigInteger VOTOSTOTALSIM        = BigInteger.valueOf(1);
    public static final BigInteger VOTOSTOTAL           = BigInteger.valueOf(2);
    public static final String NAME                     = "Pauta n";
    public static final LocalDateTime DATEINI           = LocalDateTime.parse("2022-12-08T09:00");
    public static final LocalDateTime DATEFIM           = LocalDateTime.parse("2024-12-08T18:00");
    public static final String VOTO_JA_REGISTRADO       = "Voto já cadastrado";
    public static final String SESSAO_ENCERRADA         = "Sessão encerrada ou inexistente";

    @InjectMocks
    private VotoServiceImpl service;
    @Mock
    private VotoDAOImpl dao;
    @Mock
    private PautaDAOImpl pautaDao;
    @Mock
    private ModelMapper mapper;

    private Voto voto;
    private VotoDTO votoDTO;
    private Optional<Voto> optionalVoto;
    private List<Voto> votoLista = new ArrayList<>();
    private List<VotoDTO> votoDTOLista = new ArrayList<>();
    private ApuracaoDTO apuracaoDTO;
    private List<ApuracaoDTO> apuracaoDTOLista = new ArrayList<>();
    private Pauta pauta;
    private SessaoDTO sessaoDTO;
    private List<SessaoDTO> sessaoDTOLista = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startVoto();
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(dao.create(any())).thenReturn(voto);
        when(pautaDao.GetSessao(any())).thenReturn(sessaoDTOLista);

        Voto response = service.create(votoDTO);

        assertNotNull(response);
        assertEquals(Voto.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(COD_PAUTA, response.getCodPauta());
        assertEquals(CPF, response.getCpf());
        assertEquals(VOTO, response.getVoto());
    }

    @Test
    void whenCreateThenAnDataIntegrityViolationExceptionVotoJaCadastrado() {
        when(dao.create(any())).thenReturn(voto);
        when(dao.findByCodPautaAndCpf(any(),any())).thenReturn(optionalVoto);
        when(pautaDao.GetSessao(any())).thenReturn(sessaoDTOLista);

        try{
            Voto response = service.create(votoDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(VOTO_JA_REGISTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindByCodPautaAndCpfThenReturnADataIntegrityViolationExceptionSessaEncerrada(){
        when(pautaDao.GetSessao(any())).thenThrow(new DataIntegrityViolationException(SESSAO_ENCERRADA));
        try{
            pautaDao.GetSessao(CODPAUTA);
        } catch (Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(SESSAO_ENCERRADA, ex.getMessage());
        }
    }

    @Test
    void whenFindByCodPautaThenReturnAListOfVoto() {
        when(dao.findByCodPauta(any(),any())).thenReturn(votoLista);
        List<Voto> response = service.findByCodPauta(COD_PAUTA, PageRequest.of(PAGE, SIZE));

        assertNotNull(response);
        assertEquals(Voto.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(COD_PAUTA, response.get(INDEX).getCodPauta());
        assertEquals(CPF, response.get(INDEX).getCpf());
        assertEquals(VOTO, response.get(INDEX).getVoto());
    }

    @Test
    void whenApuracaoThenReturnAListOfApuracaoDTO() {
        when(dao.GetApuracao(any())).thenReturn(apuracaoDTOLista);
        List<ApuracaoDTO> response = service.apuracao(COD_PAUTA);

        assertNotNull(response);
        assertEquals(ApuracaoDTO.class, response.get(INDEX).getClass());
        assertEquals(COD_PAUTA, response.get(INDEX).getCod_Pauta());
        assertEquals(VOTOSTOTALSIM, response.get(INDEX).getVotosTotalSim());
        assertEquals(VOTOSTOTAL, response.get(INDEX).getVotosTotal());
    }

    private void startVoto() {
        voto = new Voto(ID, COD_PAUTA, CPF, VOTO);
        votoLista.add(voto);
        votoDTO = new VotoDTO(ID, COD_PAUTA, CPF, VOTO);
        votoDTOLista.add(votoDTO);
        optionalVoto = Optional.of(new Voto(ID, COD_PAUTA, CPF, VOTO));
        apuracaoDTO = new ApuracaoDTO(COD_PAUTA, VOTOSTOTALSIM, VOTOSTOTAL);
        apuracaoDTOLista.add(apuracaoDTO);
        pauta = new Pauta(1L, NAME, DATEINI, DATEFIM);
        sessaoDTO = new SessaoDTO(COD_PAUTA);
        sessaoDTOLista.add(sessaoDTO);
    }
}