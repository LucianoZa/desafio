package br.com.desafio.domain.entity;

import br.com.desafio.model.ApuracaoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedNativeQuery(name = "Voto.GetApuracao",
        query = "select v.cod_Pauta cod_Pauta, " +
                "       (SELECT COUNT(v2.voto)  FROM voto v2 WHERE v2.cod_pauta = :codPauta AND v2.voto = 'S') votosTotalSim, " +
                "       COUNT(voto) votosTotal  " +
                "from voto v where v.cod_Pauta = :codPauta ",
        resultSetMapping = "Mapping.ApuracaoDTO")
@SqlResultSetMapping(name = "Mapping.ApuracaoDTO",
        classes = @ConstructorResult(targetClass = ApuracaoDTO.class,
                columns = {
                        @ColumnResult(name = "cod_Pauta"),
                        @ColumnResult(name = "votosTotalSim"),
                        @ColumnResult(name = "votosTotal")}))

@Entity
@Table(name = "voto")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_voto")
    private Long id;

    @Column(name = "cod_pauta")
    private Long codPauta;

    @Basic(optional = false)
    @Size(max = 11)
    @NotNull
    private String cpf;

    @Size(max = 1)
    @NotNull
    private String voto;

}
