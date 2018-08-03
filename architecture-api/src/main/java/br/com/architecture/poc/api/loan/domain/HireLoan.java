package br.com.architecture.poc.api.loan.domain;

import br.com.architecture.poc.api.common.Currency;
import br.com.architecture.poc.api.common.MoneyValue;
import br.com.architecture.poc.api.common.SSN;
import br.com.architecture.poc.api.common.UseCase;
import lombok.*;

import java.io.Serializable;

/**
 * @author Rafael Torquato
 */
@RequiredArgsConstructor
public class HireLoan implements UseCase<HireLoan.Request, HireLoan.Response> {

    private final HirerRepository hirerRepository;
    private final LoanRepository loanRepository;

    @Override
    public Response execute(Request req) throws LoanException {
        Hirer hirer = hirerRepository.bySocialSecurityNumber(new SSN(req.ssn));
        errorIfHirerDoesNotExist(hirer);

        MoneyValue moneyValue = new MoneyValue(req.value, Currency.valueOf(req.currency));
        LoanInstallment loanInstallment = new LoanInstallment(req.loanInstallment);
        Loan loan = hirer.hireLoan(moneyValue, loanInstallment);
        loanRepository.store(loan);

        return new Response(loan.getIdentifier().toString());
    }

    private void errorIfHirerDoesNotExist(Hirer hirer) throws LoanException {
        if (hirer == null)
            throw new LoanException(ErrorMessage.HIRER_DOES_NOT_EXIST);
    }


    @Data
    @AllArgsConstructor
    public static class Request implements Serializable {
        private String ssn;
        private Double value;
        private String currency;
        private Integer loanInstallment;
    }

    @Data
    public static class Response implements Serializable {
        private final String identifier;
    }
}
