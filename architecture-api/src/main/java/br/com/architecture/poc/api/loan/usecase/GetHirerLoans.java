package br.com.architecture.poc.api.loan.usecase;

import br.com.architecture.poc.api.common.SSN;
import br.com.architecture.poc.api.common.UseCase;
import br.com.architecture.poc.api.loan.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rafael Torquato
 */
@RequiredArgsConstructor
public class GetHirerLoans implements
        UseCase<GetHirerLoans.Request, List<GetHirerLoans.Response>> {

    private final LoanRepository loanRepository;
    private final HirerRepository hirerRepository;

    @Override
    public List<Response> execute(Request request) throws LoanException {
        Hirer hirer = hirerRepository.bySocialSecurityNumber(new SSN(request.hirerSsn))
                .orElseThrow(() -> new LoanException(ErrorMessage.HIRER_DOES_NOT_EXIST));
        errorIfHirerIsDead(hirer);

        return loanRepository.loansOfHirer(hirer).stream()
                .map(Response::new)
                .collect(Collectors.toList());
    }

    private void errorIfHirerIsDead(Hirer hirer) throws LoanException {
        if (hirer.dead()) throw new LoanException(ErrorMessage.HIRER_IS_DEAD);
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String hirerSsn;
    }

    @Data
    public class Response {
        private Double value;
        private String currency;
        private Integer loanInstallmentQuantity;

        public Response(Loan l) {
            value = l.getMoneyValue().doubleValue();
            currency = l.getMoneyValue().currency().toString();
            loanInstallmentQuantity = l.getLoanInstallment().intValue();
        }
    }

}
