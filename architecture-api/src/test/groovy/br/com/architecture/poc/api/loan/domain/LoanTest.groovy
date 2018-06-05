package br.com.architecture.poc.api.loan.domain

import br.com.architecture.poc.api.common.Currency
import br.com.architecture.poc.api.common.SSN
import br.com.architecture.poc.api.common.MoneyValue
import spock.lang.Specification

/**
 * @author Rafael Torquato
 */
class LoanTest extends Specification {

    def "deve falhar ao criar Emprestimo com valor null"() {
        given:
        def valor = null
        def parcelas = new LoanInstallment(10)
        def contratante = new Hirer(
                new SSN("99198720163"),
                new Date()
        )

        when:
        new Loan(valor, parcelas, contratante)

        then:
        thrown(NullPointerException.class)
    }

    def "deve falhar ao criar Emprestimo com parcelas null"() {
        given:
        def valor = new MoneyValue(10000.00, Currency.REAL)
        def parcelas = null
        def contratante = new Hirer(
                new SSN("99198720163"),
                new Date()
        )

        when:
        new Loan(valor, parcelas, contratante)

        then:
        thrown(NullPointerException.class)
    }

    def "deve falhar ao criar Emprestimo com contratante null"() {
        given:
        def valor = new MoneyValue(10000.00, Currency.REAL)
        def parcelas = null
        def contratante = null

        when:
        new Loan(valor, parcelas, contratante)

        then:
        thrown(NullPointerException.class)
    }
}
