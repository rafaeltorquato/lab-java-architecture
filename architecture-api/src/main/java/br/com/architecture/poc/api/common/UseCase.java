package br.com.architecture.poc.api.common;

import br.com.architecture.poc.api.loan.domain.LoanException;

/**
 * @author Rafael Torquato
 */
public interface UseCase<E, S> {
    S executar(E entrada) throws LoanException;
}
