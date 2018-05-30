package br.coop.cooperforte.architecture.api.comum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author Rafael Torquato (Mirante Tecnologia)
 */
@Getter
@AllArgsConstructor
public enum Moeda implements Serializable {
    USD("en", "US"), REAL("pt", "BR");

    private final String idioma;
    private final String pais;

}
