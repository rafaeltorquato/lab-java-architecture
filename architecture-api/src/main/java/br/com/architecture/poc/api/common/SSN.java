package br.com.architecture.poc.api.common;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Rafael Torquato
 */
@EqualsAndHashCode(of = "unmasked")
public final class SSN implements Serializable {

    private final String unmasked;
    private String masked;

    public SSN(String value) {
        this.unmasked = value.replaceAll("\\D", "");
        if (this.unmasked.length() != 9)
            throw new IllegalArgumentException("Invalid Social Security Number (SSN)!");
    }

    public String mask() {
        if (masked == null)
            masked = unmasked.replaceAll("(\\d{3})(\\d{2})(\\d{4})", "$1-$2-$3");
        return masked;
    }

    @Override
    public String toString() {
        return unmasked;
    }
}
