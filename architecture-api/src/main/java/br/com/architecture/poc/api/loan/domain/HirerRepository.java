package br.com.architecture.poc.api.loan.domain;

import br.com.architecture.poc.api.common.SSN;

import java.util.Optional;

/**
 * @author Rafael Torquato
 */
public interface HirerRepository {
    Optional<Hirer> bySocialSecurityNumber(SSN ssn);
}
