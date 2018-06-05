package br.com.architecture.poc.api.loan.domain;

/**
 * @author Rafael Torquato
 */
public final class LoanInstallment extends Number {
    private final Integer quantity;

    public LoanInstallment(Integer quantity) {
        this.quantity = quantity;
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0.");
    }

    @Override
    public int intValue() {
        return quantity;
    }

    @Override
    public long longValue() {
        return quantity.longValue();
    }

    @Override
    public float floatValue() {
        return quantity.floatValue();
    }

    @Override
    public double doubleValue() {
        return quantity.doubleValue();
    }
}
