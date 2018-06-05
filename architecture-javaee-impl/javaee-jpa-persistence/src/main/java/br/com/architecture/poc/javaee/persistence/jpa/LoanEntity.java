package br.com.architecture.poc.javaee.persistence.jpa;

import br.com.architecture.poc.api.loan.domain.Loan;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @author Rafael Torquato
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class LoanEntity implements Serializable {

    @Id
    private UUID identifier;
    private String ssn;
    private BigDecimal value;
    private Integer loanInstallment;
    private String currency;
    @Temporal(TemporalType.TIMESTAMP)
    private Date contractDate;

    public LoanEntity(Loan loan) {
        ssn = loan.getHirer().getSsn().toString();
        identifier = loan.getIdentifier();
        contractDate = loan.getContractDate();
        loanInstallment = loan.getLoanInstallment().intValue();
        value = new BigDecimal(loan.getMoneyValue().doubleValue());
        currency = loan.getMoneyValue().currency().toString();
    }
}
