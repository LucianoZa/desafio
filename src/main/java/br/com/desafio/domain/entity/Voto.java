package br.com.desafio.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Entity
@Table(name = "voto")
@Data
public class Voto {

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_pauta")
    private Long cod_pauta;

    @Basic(optional = false)
    @Size(max = 11)
    @NotNull
    private String cpf;

    @Size(max = 1)
    @NotNull
    private String voto;

}
