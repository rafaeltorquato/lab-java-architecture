package br.com.architecture.poc.api.loan.domain

import br.com.architecture.poc.api.common.Currency
import br.com.architecture.poc.api.common.MoneyValue
import br.com.architecture.poc.api.common.SSN
import spock.lang.Specification

/**
 * @author Rafael Torquato
 */
class LoanTest extends Specification {

    Hirer hirer
    MoneyValue value
    LoanInstallment loanInstallment

    def setup() {
        hirer = new Hirer(
                new SSN("991987201"),
                new Date()
        )
        value = new MoneyValue(10000.00, Currency.REAL)
        loanInstallment = new LoanInstallment(10)
    }

    def "Should fail when create a Loan with null value"() {
        given:
        def value = null


        when:
        new Loan(value, loanInstallment, hirer)

        then:
        thrown(NullPointerException.class)
    }

    def "Should fail when create a loan with null installment"() {
        given:
        def loanInstallment = null

        when:
        new Loan(value, loanInstallment, hirer)

        then:
        thrown(NullPointerException.class)
    }

    def "Should fail when create a loan with a null hirer"() {
        given:
        def hirer = null

        when:
        new Loan(value, loanInstallment, hirer)

        then:
        thrown(NullPointerException.class)
    }
}
