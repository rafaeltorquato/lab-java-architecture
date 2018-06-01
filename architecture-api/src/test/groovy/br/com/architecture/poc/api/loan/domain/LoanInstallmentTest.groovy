package br.com.architecture.poc.api.loan.domain

import spock.lang.Specification

/**
 * @author Rafael Torquato
 */
class LoanInstallmentTest extends Specification {

    def "deve falhar ao criar Parcelas com valor negativo"() {
        given:
        def quantidade = -1

        when:
        new LoanInstallment(quantidade)

        then:
        thrown(IllegalArgumentException.class)
    }

    def "deve falhar ao criar Parcelas com valor zero"() {
        given:
        def quantidade = 0

        when:
        new LoanInstallment(quantidade)

        then:
        thrown(IllegalArgumentException.class)
    }

    def "deve falhar ao criar Parcelas com valor null"() {
        given:
        def quantidade = null

        when:
        new LoanInstallment(quantidade)

        then:
        thrown(NullPointerException.class)
    }

}
