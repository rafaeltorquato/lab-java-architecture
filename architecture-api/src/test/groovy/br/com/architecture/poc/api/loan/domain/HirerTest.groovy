package br.com.architecture.poc.api.loan.domain

import br.com.architecture.poc.api.common.SSN
import spock.lang.Specification

/**
 * @author Rafael Torquato
 */
class HirerTest extends Specification {

    def "Should fail when create a Hirer without null ssn"() {
        given:
        def ssn = null
        def birthDate = new Date()

        when:
        new Hirer(ssn, birthDate)

        then:
        thrown(NullPointerException.class)
    }

    def "Should fail when create a Hirer with null no birthDate"() {
        given:
        def ssn = new SSN("991987201")
        def birthDate = null

        when:
        new Hirer(ssn, birthDate)

        then:
        thrown(NullPointerException.class)
    }

}
