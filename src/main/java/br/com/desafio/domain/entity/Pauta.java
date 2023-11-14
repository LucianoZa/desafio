package br.com.desafio.domain.entity;

import br.com.desafio.model.SessaoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NamedNativeQuery(name = "Pauta.GetSessao",
        query = "select cod_pauta from pauta where dt_ini_votacao <= now() and now() <= dt_fim_votacao and cod_pauta = :codPauta ",
        resultSetMapping = "Mapping.SessaoDTO")
@SqlResultSetMapping(name = "Mapping.SessaoDTO",
        classes = @ConstructorResult(targetClass = SessaoDTO.class,
                columns = {
                        @ColumnResult(name = "cod_pauta")}))

@Entity
@Table(name = "pauta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_pauta")
    private Long id;

    @Size(max = 200)
    @NotNull
    @Column(name = "nom_pauta", unique = true)
    private String nomPauta;

    @Column(name = "dt_ini_votacao", columnDefinition = "TIMESTAMP")
    private LocalDateTime dtIniVotacao;

    @Column(name = "dt_fim_votacao", columnDefinition = "TIMESTAMP")
    private LocalDateTime dtFimVotacao;

}
