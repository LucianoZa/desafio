package br.com.desafio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.Objects;

public class CodigoPautaDTO {

        @JsonProperty("cod_Pauta")
        private BigInteger cod_Pauta;

        public CodigoPautaDTO() {
        }

        public BigInteger getCodPauta() {
                return cod_Pauta;
        }

        public void setCodPauta(BigInteger cod_Pauta) {
                this.cod_Pauta = cod_Pauta;
        }

        public CodigoPautaDTO(BigInteger cod_Pauta) {
                this.cod_Pauta = cod_Pauta;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                CodigoPautaDTO that = (CodigoPautaDTO) o;
                return Objects.equals(cod_Pauta, that.cod_Pauta);
        }

        @Override
        public int hashCode() {
                return Objects.hash(cod_Pauta);
        }

        @Override
        public String toString() {
                return "CodigoPautaDTO{" +
                        "cod_Pauta=" + cod_Pauta +
                        '}';
        }

}
