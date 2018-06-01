package br.com.architecture.poc.api.loan.domain;

import java.util.List;

/**
 * @author Rafael Torquato
 */
public interface LoanRepository {
    void store(Loan loan);

    List<Loan> loansOfHirer(Hirer hirer);
}
