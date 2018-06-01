package br.com.architecture.poc.api.domain

import spock.lang.Specification

/**
 * @author Rafael Torquato
 */
class ParcelasTest extends Specification {

    def "deve falhar ao criar Parcelas com valor negativo"() {
        given:
        def quantidade = -1

        when:
        new Parcelas(quantidade)

        then:
        thrown(IllegalArgumentException.class)
    }

    def "deve falhar ao criar Parcelas com valor zero"() {
        given:
        def quantidade = 0

        when:
        new Parcelas(quantidade)

        then:
        thrown(IllegalArgumentException.class)
    }

    def "deve falhar ao criar Parcelas com valor null"() {
        given:
        def quantidade = null

        when:
        new Parcelas(quantidade)

        then:
        thrown(NullPointerException.class)
    }

}
