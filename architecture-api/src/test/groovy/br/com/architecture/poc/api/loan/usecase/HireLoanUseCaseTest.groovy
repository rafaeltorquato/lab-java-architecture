package br.com.architecture.poc.api.loan.usecase

import br.com.architecture.poc.api.common.Currency
import br.com.architecture.poc.api.common.SSN
import br.com.architecture.poc.api.loan.domain.ErrorMessage
import br.com.architecture.poc.api.loan.domain.HirerRepository
import br.com.architecture.poc.api.loan.domain.LoanException
import br.com.architecture.poc.api.loan.domain.LoanRepository
import br.com.architecture.poc.api.loan.usecase.HireLoanUseCase
import spock.lang.Specification

/**
 * @author Rafael Torquato
 */
class HireLoanUseCaseTest extends Specification {

    def "Should fail because hirer does not exist"() {
        given:
        def hirerRepository = Mock(HirerRepository)
        hirerRepository.bySocialSecurityNumber(_ as SSN) >> { Optional.empty() }

        def request = new HireLoanUseCase.Request()
        request.ssn = "919872016"
        request.value = 1000.00D
        request.currency = Currency.USD.toString()
        request.loanInstallment = 10

        def useCase = new HireLoanUseCase(
                hirerRepository,
                Mock(LoanRepository)
        )
        when:
        useCase.execute(request)

        then:
        def e = thrown(LoanException.class)
        e.errorMessage == ErrorMessage.HIRER_DOES_NOT_EXIST
    }

}
