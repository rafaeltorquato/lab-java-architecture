package br.com.architecture.poc.api.common;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Rafael Torquato
 */
@EqualsAndHashCode(of = "raw")
public final class SSN implements Serializable {

    private final String raw;
    private String formatted;

    public SSN(String value) {
        this.raw = value.replaceAll("\\D", "");
        if (this.raw.length() != 9)
            throw new IllegalArgumentException("Not valid SSN.");
    }

    public String formatted() {
        if (formatted == null)
            formatted = raw.replaceAll("(\\d{3})(\\d{2})(\\d{4})", "$1-$2-$3");
        return formatted;
    }

    @Override
    public String toString() {
        return raw;
    }
}
