package br.com.architecture.poc.api.common;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Rafael Torquato
 */
public enum Currency implements Serializable {
    USD("en", "US"), REAL("pt", "BR");

    private Locale locale;

    Currency(String language, String country) {
        locale = new Locale(language, country);
    }

    public String format(Value value) {
        return NumberFormat.getCurrencyInstance(locale).format(value);
    }
}
