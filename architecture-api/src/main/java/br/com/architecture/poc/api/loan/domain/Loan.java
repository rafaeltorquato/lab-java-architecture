package br.com.architecture.poc.api.loan.domain;

import br.com.architecture.poc.api.common.MoneyValue;
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
@EqualsAndHashCode(of = "identifier")
public class Loan implements Serializable {

    private final UUID identifier;
    private final MoneyValue moneyValue;
    private final LoanInstallment loanInstallment;
    private final Hirer hirer;
    private final Date contractDate;

    public Loan(MoneyValue moneyValue, LoanInstallment loanInstallment, Hirer hirer) {
        this.identifier = UUID.randomUUID();
        this.moneyValue = Objects.requireNonNull(moneyValue);
        this.loanInstallment = Objects.requireNonNull(loanInstallment);
        this.hirer = Objects.requireNonNull(hirer);
        this.contractDate = new Date();
    }
}
