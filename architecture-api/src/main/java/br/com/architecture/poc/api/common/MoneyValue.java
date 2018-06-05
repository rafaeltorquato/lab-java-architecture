package br.com.architecture.poc.api.common;

import java.util.Objects;

/**
 * @author Rafael Torquato
 */
public final class MoneyValue extends Number {
    private final Double value;
    private final Currency currency;
    private transient String toStringCache;

    public MoneyValue(Double value, Currency currency) {
        this.value = Objects.requireNonNull(value);
        this.currency = Objects.requireNonNull(currency);
    }

    @Override
    public String toString() {
        if (toStringCache == null)
            toStringCache = currency.format(this);
        return toStringCache;
    }

    public Currency currency() {
        return currency;
    }

    @Override
    public int intValue() {
        return value.intValue();
    }

    @Override
    public long longValue() {
        return value.longValue();
    }

    @Override
    public float floatValue() {
        return value.floatValue();
    }

    @Override
    public double doubleValue() {
        return value;
    }
}
