package br.com.architecture.poc.api.common;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * @author Rafael Torquato
 */
public final class Valor extends Number {
    private final Double valor;
    private final Moeda moeda;
    private transient String toStringCache;

    public Valor(Double valor, Moeda moeda) {
        this.valor = Objects.requireNonNull(valor);
        this.moeda = Objects.requireNonNull(moeda);
    }

    @Override
    public String toString() {
        if (toStringCache == null)
            toStringCache = NumberFormat.getCurrencyInstance(new Locale(moeda.getIdioma(), moeda.getPais())).format(valor);
        return toStringCache;
    }

    public Moeda moeda() {
        return moeda;
    }

    @Override
    public int intValue() {
        return valor.intValue();
    }

    @Override
    public long longValue() {
        return valor.longValue();
    }

    @Override
    public float floatValue() {
        return valor.floatValue();
    }

    @Override
    public double doubleValue() {
        return valor;
    }
}
