package br.com.architecture.poc.api.common;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Rafael Torquato
 */
@EqualsAndHashCode(of = "unmasked")
public final class SocialSecurityNumber implements Serializable {

    private final String unmasked;
    private String masked;

    public SocialSecurityNumber(String value) {
        this.unmasked = value.replaceAll("\\D", "");
        if (this.unmasked.length() != 11)
            throw new IllegalArgumentException("SocialSecurityNumber invalid.");
    }

    public String mask() {
        if (masked == null)
            masked = unmasked.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        return masked;
    }

    @Override
    public String toString() {
        return unmasked;
    }
}
