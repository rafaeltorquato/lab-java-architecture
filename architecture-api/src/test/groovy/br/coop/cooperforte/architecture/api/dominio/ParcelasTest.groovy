package br.coop.cooperforte.architecture.api.dominio

import spock.lang.Specification

/**
 * @author Rafael Torquato (Mirante Tecnologia)
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
