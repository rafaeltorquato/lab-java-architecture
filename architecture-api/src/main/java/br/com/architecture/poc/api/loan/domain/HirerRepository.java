package br.com.architecture.poc.api.loan.domain;

import br.com.architecture.poc.api.common.SocialSecurityNumber;

/**
 * @author Rafael Torquato
 */
public interface HirerRepository {
    Hirer bySocialSecurityNumber(SocialSecurityNumber socialSecurityNumber);
}
