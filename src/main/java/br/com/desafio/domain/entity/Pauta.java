package br.com.desafio.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "pauta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cod_pauta")
    private Long codPauta;

    @Size(max = 200)
    @NotNull
    @Column(name = "nom_pauta")
    private String nomPauta;

    @Column(name = "dt_ini_votacao", columnDefinition = "TIMESTAMP")
    private LocalDateTime dtIniVotacao;

    @Column(name = "dt_fim_votacao", columnDefinition = "TIMESTAMP")
    private LocalDateTime dtFimVotacao;

}
