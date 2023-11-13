package br.com.desafio.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "voto")
@Data
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_voto")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "cod_pauta")
//    private Pauta pauta;

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
