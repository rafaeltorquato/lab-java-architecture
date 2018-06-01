package br.com.architecture.poc.javaee.persistence.stubs;

import br.com.architecture.poc.api.loan.domain.Hirer;
import br.com.architecture.poc.api.loan.domain.Loan;
import br.com.architecture.poc.api.loan.domain.LoanRepository;

import javax.ejb.Singleton;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Rafael Torquato
 */
@Singleton
public class LoanRepositoryStubs implements LoanRepository {

    private Map<UUID, Loan> emprestimos = new ConcurrentHashMap<>();

    @Override
    public void store(Loan loan) {
        emprestimos.putIfAbsent(loan.getIdentificador(), loan);
    }

    @Override
    public List<Loan> loansOfHirer(Hirer hirer) {
        return emprestimos.values().stream()
                .filter(e -> e.getHirer().equals(hirer))
                .collect(Collectors.toList());
    }
}
