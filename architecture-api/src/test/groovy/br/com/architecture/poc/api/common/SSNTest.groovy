package br.com.architecture.poc.api.common

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Rafael Torquato
 */
class SSNTest extends Specification {


    @Unroll
    def "Should fail when create a SSN with eight characters"() {
        given:
        def cpfString = '12345678'

        when:
        new SSN(cpfString)

        then:
        thrown(IllegalArgumentException.class)
    }

    def "Should fail when create a SSN with null value"() {
        given:
        def ssnString = null

        when:
        new SSN(ssnString)

        then:
        thrown(NullPointerException.class)
    }

    def "should format a SSN"() {
        given:
        def ssnString = '991982016'

        when:
        def formatted = new SSN(ssnString).formatted()

        then:
        formatted == '991-98-2016'
    }

}
