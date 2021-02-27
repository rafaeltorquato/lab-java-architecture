package br.com.architecture.poc.api.loan.domain;

import br.com.architecture.poc.api.common.MoneyValue;
import br.com.architecture.poc.api.common.SSN;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Rafael Torquato
 */
@EqualsAndHashCode(of = "ssn")
public class Hirer implements Serializable {

    @Getter
    private final SSN ssn;
    @Getter
    private final Date birthDate;
    @Setter
    private Date dateOfDeath;

    public Hirer(SSN SSN, Date birthDate) {
        this.ssn = Objects.requireNonNull(SSN);
        this.birthDate = Objects.requireNonNull(birthDate);
    }

    public boolean dead() {
        return dateOfDeath != null;
    }

    public Optional<Date> getDateOfDeath() {
        return Optional.ofNullable(dateOfDeath);
    }

    public Loan hireLoan(MoneyValue moneyValue, LoanInstallment loanInstallment) throws LoanException {
        errorIfIamDead();
        return new Loan(moneyValue, loanInstallment, this);
    }

    private void errorIfIamDead() throws LoanException {
        if(dead()) throw new LoanException(ErrorMessage.HIRER_IS_DEAD);
    }
}
