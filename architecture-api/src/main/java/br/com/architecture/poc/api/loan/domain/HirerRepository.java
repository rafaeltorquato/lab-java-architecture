package br.com.architecture.poc.api.loan.domain;

import br.com.architecture.poc.api.common.SSN;

/**
 * @author Rafael Torquato
 */
public interface HirerRepository {
    Hirer bySocialSecurityNumber(SSN ssn);
}
