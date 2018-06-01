package br.com.architecture.poc.api.loan.domain;

import br.com.architecture.poc.api.common.SocialSecurityNumber;
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
        UseCase<GetHirerLoans.Solicitacao, List<GetHirerLoans.Resposta>> {

    private final LoanRepository loanRepository;
    private final HirerRepository hirerRepository;

    @Override
    public List<Resposta> executar(Solicitacao entrada) throws LoanException {
        Hirer hirer = hirerRepository.bySocialSecurityNumber(new SocialSecurityNumber(entrada.cpfContratante));
        erroSeContratanteInexistente(hirer);
        erroSeContratanteMorto(hirer);

        return loanRepository.loansOfHirer(hirer).stream()
                .map(e -> {
                    Resposta r = new Resposta();
                    r.currency = e.getValue().currency().toString();
                    r.loanInstallmentQuantity = e.getLoanInstallment().intValue();
                    r.valor = e.getValue().doubleValue();
                    return r;
                })
                .collect(Collectors.toList());
    }

    private void erroSeContratanteInexistente(Hirer hirer) throws LoanException {
        if (hirer == null)
            throw new LoanException(ErrorMessage.HIRER_DOES_NOT_EXIST);
    }

    private void erroSeContratanteMorto(Hirer hirer) throws LoanException {
        if (hirer.morto())
            throw new LoanException(ErrorMessage.HIRER_IS_DEAD);
    }

    @Data
    @AllArgsConstructor
    public static class Solicitacao {
        private String cpfContratante;
    }

    @Data
    public class Resposta {
        private Double valor;
        private String currency;
        private Integer loanInstallmentQuantity;
    }

}
