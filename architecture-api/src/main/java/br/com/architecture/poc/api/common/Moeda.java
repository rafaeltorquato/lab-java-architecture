package br.com.architecture.poc.api.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author Rafael Torquato
 */
@Getter
@AllArgsConstructor
public enum Moeda implements Serializable {
    USD("en", "US"), REAL("pt", "BR");

    private final String idioma;
    private final String pais;

}
