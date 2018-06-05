package br.com.architecture.poc.api.loan.domain

import br.com.architecture.poc.api.common.Currency
import spock.lang.Specification

/**
 * @author Rafael Torquato
 */
class HireLoanTest extends Specification {

    def "Should fail because hirer does not exist"() {
        given:
        def hirerRepository = Mock(HirerRepository)
        hirerRepository.bySocialSecurityNumber(_) >> { null }

        def loanRepository = Mock(LoanRepository)
        loanRepository.store(_) >> {}

        def request = new HireLoan.Request()
        request.ssn = "919872016"
        request.value = 1000.00D
        request.currency = Currency.USD.toString()
        request.loanInstallment = 10

        def useCase = new HireLoan(
                hirerRepository,
                loanRepository
        )
        when:
        useCase.execute(request)

        then:
        def e = thrown(LoanException.class)
        e.errorMessage == ErrorMessage.HIRER_DOES_NOT_EXIST
        1 * hirerRepository.bySocialSecurityNumber(_)
        0 * loanRepository.store(_)
    }

}
