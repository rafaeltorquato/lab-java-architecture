package br.com.architecture.poc.api.loan.domain

import br.com.architecture.poc.api.common.SocialSecurityNumber
import spock.lang.Specification

/**
 * @author Rafael Torquato
 */
class HirerTest extends Specification {

    def "deve falhar ao criar Contratante com cpf null"() {
        given:
        def cpf = null
        def dataNascimento = new Date()
        def emancipado = false

        when:
        new Hirer(cpf, dataNascimento, emancipado)

        then:
        thrown(NullPointerException.class)
    }

    def "deve falhar ao criar Contratante com dataNascimento null"() {
        given:
        def cpf = new SocialSecurityNumber("99198720163")
        def dataNascimento = null
        def emancipado = false

        when:
        new Hirer(cpf, dataNascimento, emancipado)

        then:
        thrown(NullPointerException.class)
    }

    def "deve falhar ao criar Contratante com indicacao de emancipacao null"() {
        given:
        def cpf = new SocialSecurityNumber("99198720163")
        def dataNascimento = new Date()
        def emancipado = null

        when:
        new Hirer(cpf, dataNascimento, emancipado)

        then:
        thrown(NullPointerException.class)
    }

    //TODO Testar inimputabilidade
    //TODO Testar maioridade
}
