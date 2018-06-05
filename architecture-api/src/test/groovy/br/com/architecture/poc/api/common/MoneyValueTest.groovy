package br.com.architecture.poc.api.common

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Rafael Torquato
 */
class MoneyValueTest extends Specification {

    def "Should fail when create a MoneyValue with null value"() {
        given:
        def value = null
        def currency = Currency.REAL

        when:
        new MoneyValue(value, currency)

        then:
        thrown(NullPointerException.class)
    }

    def "Should fail when create a MoneyValue with null currency"() {
        given:
        def value = 1.0
        def currency = null

        when:
        new MoneyValue(value, currency)

        then:
        thrown(NullPointerException.class)
    }

    @Unroll
    def "MoneyValue should be formatted in #currency"() {
        given:
        def valueLiteral = 1.0

        when:
        def value = new MoneyValue(valueLiteral, currency)

        then:
        value.toString() == formattedValue

        where:
        currency      | formattedValue
        Currency.REAL | 'R$ 1,00'
        Currency.USD  | '$1.00'
    }
}
