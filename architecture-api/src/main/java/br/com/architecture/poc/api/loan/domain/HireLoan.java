package br.com.architecture.poc.api.loan.domain;

import br.com.architecture.poc.api.common.Currency;
import br.com.architecture.poc.api.common.SSN;
import br.com.architecture.poc.api.common.UseCase;
import br.com.architecture.poc.api.common.MoneyValue;
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
    public Response executar(Request req) throws LoanException {
        Hirer hirer = hirerRepository.bySocialSecurityNumber(new SSN(req.cpf));
        erroSeContratanteInexistente(hirer);

        Loan loan = new Loan(
                new MoneyValue(req.valor, Currency.valueOf(req.moeda)),
                new LoanInstallment(req.quantidadeParcelas),
                hirer
        );
        loanRepository.store(loan);

        return new Response(
                loan.getIdentificador().toString()
        );
    }

    private void erroSeContratanteInexistente(Hirer hirer) throws LoanException {
        if (hirer == null)
            throw new LoanException(ErrorMessage.HIRER_DOES_NOT_EXIST);
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class Request implements Serializable {
        private String cpf;
        private Double valor;
        private String moeda;
        private Integer quantidadeParcelas;
    }

    @Data
    public class Response implements Serializable {
        private final String identificador;
    }
}
