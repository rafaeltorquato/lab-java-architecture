package br.com.architecture.poc.api.loan.domain

import br.com.architecture.poc.api.common.SSN
import spock.lang.Specification

/**
 * @author Rafael Torquato
 */
class HirerTest extends Specification {

    def "deve falhar ao criar Contratante com cpf null"() {
        given:
        def cpf = null
        def dataNascimento = new Date()

        when:
        new Hirer(cpf, dataNascimento)

        then:
        thrown(NullPointerException.class)
    }

    def "deve falhar ao criar Contratante com dataNascimento null"() {
        given:
        def cpf = new SSN("99198720163")
        def dataNascimento = null

        when:
        new Hirer(cpf, dataNascimento)

        then:
        thrown(NullPointerException.class)
    }

}
