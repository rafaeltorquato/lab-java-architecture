package br.com.architecture.poc.api.common

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Rafael Torquato
 */
class ValueTest extends Specification {

    def "deve falhar ao criar Valor com valor null"() {
        given:
        def valor = null
        def moeda = Currency.REAL

        when:
        new Value(valor, moeda)

        then:
        thrown(NullPointerException.class)
    }

    def "deve falhar ao criar Valor com moeda null"() {
        given:
        def valor = 1.0
        def moeda = null

        when:
        new Value(valor, moeda)

        then:
        thrown(NullPointerException.class)
    }

    @Unroll
    def "valor deve ser formatado de acordo com a moeda #moeda"() {
        given:
        def valorDouble = 1.0

        when:
        def valor = new Value(valorDouble, moeda)

        then:
        valor.toString() == valorFormatado

        where:
        moeda         | valorFormatado
        Currency.REAL | 'R$ 1,00'
        Currency.USD  | '$1.00'
    }
}
