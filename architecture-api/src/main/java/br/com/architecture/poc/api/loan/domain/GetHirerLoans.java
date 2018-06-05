package br.com.architecture.poc.api.loan.domain;

import br.com.architecture.poc.api.common.SSN;
import br.com.architecture.poc.api.common.UseCase;
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
    public List<Response> execute(Request entrada) throws LoanException {
        Hirer hirer = hirerRepository.bySocialSecurityNumber(new SSN(entrada.hirerSsn));
        erroSeContratanteInexistente(hirer);
        erroSeContratanteMorto(hirer);

        return loanRepository.loansOfHirer(hirer).stream()
                .map(e -> {
                    Response r = new Response();
                    r.currency = e.getMoneyValue().currency().toString();
                    r.loanInstallmentQuantity = e.getLoanInstallment().intValue();
                    r.valor = e.getMoneyValue().doubleValue();
                    return r;
                })
                .collect(Collectors.toList());
    }

    private void erroSeContratanteInexistente(Hirer hirer) throws LoanException {
        if (hirer == null)
            throw new LoanException(ErrorMessage.HIRER_DOES_NOT_EXIST);
    }

    private void erroSeContratanteMorto(Hirer hirer) throws LoanException {
        if (hirer.dead())
            throw new LoanException(ErrorMessage.HIRER_IS_DEAD);
    }

    @Data
    @AllArgsConstructor
    public static class Request {
        private String hirerSsn;
    }

    @Data
    public class Response {
        private Double valor;
        private String currency;
        private Integer loanInstallmentQuantity;
    }

}
