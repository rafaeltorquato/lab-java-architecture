package br.com.architecture.poc.api.domain;

/**
 * @author Rafael Torquato
 */
public final class Parcelas extends Number {
    private final Integer quantidade;

    public Parcelas(Integer quantidade) {
        this.quantidade = quantidade;
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade de parcelas n\u00E3o deve maior que 0.");
    }

    @Override
    public int intValue() {
        return quantidade;
    }

    @Override
    public long longValue() {
        return quantidade.longValue();
    }

    @Override
    public float floatValue() {
        return quantidade.floatValue();
    }

    @Override
    public double doubleValue() {
        return quantidade.doubleValue();
    }
}
