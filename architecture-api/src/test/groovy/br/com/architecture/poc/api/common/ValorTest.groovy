package br.com.architecture.poc.api.common

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Rafael Torquato
 */
class ValorTest extends Specification {

    def "deve falhar ao criar Valor com valor null"() {
        given:
        def valor = null
        def moeda = Moeda.REAL

        when:
        new Valor(valor, moeda)

        then:
        thrown(NullPointerException.class)
    }

    def "deve falhar ao criar Valor com moeda null"() {
        given:
        def valor = 1.0
        def moeda = null

        when:
        new Valor(valor, moeda)

        then:
        thrown(NullPointerException.class)
    }

    @Unroll
    def "valor deve ser formatado de acordo com a moeda #moeda"() {
        given:
        def valorDouble = 1.0

        when:
        def valor = new Valor(valorDouble, moeda)

        then:
        valor.toString() == valorFormatado

        where:
        moeda      | valorFormatado
        Moeda.REAL | 'R$ 1,00'
        Moeda.USD  | '$1.00'
    }
}
