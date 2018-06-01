package br.com.architecture.poc.api.loan.domain;

import br.com.architecture.poc.api.common.Value;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Rafael Torquato
 */
@Getter
@EqualsAndHashCode(of = "identificador")
public class Loan implements Serializable {

    private final UUID identificador;
    private final Value value;
    private final LoanInstallment loanInstallment;
    private final Hirer hirer;
    private final Date contractDate;

    public Loan(Value value, LoanInstallment loanInstallment, Hirer hirer) {
        this.identificador = UUID.randomUUID();
        this.value = Objects.requireNonNull(value);
        this.loanInstallment = Objects.requireNonNull(loanInstallment);
        this.hirer = Objects.requireNonNull(hirer);
        this.contractDate = new Date();
    }
}
