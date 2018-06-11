package br.com.architecture.poc.javaee.persistence.stubs;

import br.com.architecture.poc.api.loan.domain.Hirer;
import br.com.architecture.poc.api.loan.domain.Loan;
import br.com.architecture.poc.api.loan.domain.LoanRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Rafael Torquato
 */
public class LoanRepositoryStubs implements LoanRepository {

    private Map<UUID, Loan> loanList = new ConcurrentHashMap<>();

    @Override
    public void store(Loan loan) {
        loanList.putIfAbsent(loan.getIdentifier(), loan);
    }

    @Override
    public List<Loan> loansOfHirer(Hirer hirer) {
        return loanList.values().stream()
                .filter(e -> e.getHirer().equals(hirer))
                .collect(Collectors.toList());
    }
}