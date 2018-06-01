package br.com.architecture.poc.api.domain

import br.com.architecture.poc.api.common.CPF
import spock.lang.Specification

/**
 * @author Rafael Torquato
 */
class ContratanteTest extends Specification {

    def "deve falhar ao criar Contratante com cpf null"() {
        given:
        def cpf = null
        def dataNascimento = new Date()
        def emancipado = false

        when:
        new Contratante(cpf, dataNascimento, emancipado)

        then:
        thrown(NullPointerException.class)
    }

    def "deve falhar ao criar Contratante com dataNascimento null"() {
        given:
        def cpf = new CPF("99198720163")
        def dataNascimento = null
        def emancipado = false

        when:
        new Contratante(cpf, dataNascimento, emancipado)

        then:
        thrown(NullPointerException.class)
    }

    def "deve falhar ao criar Contratante com indicacao de emancipacao null"() {
        given:
        def cpf = new CPF("99198720163")
        def dataNascimento = new Date()
        def emancipado = null

        when:
        new Contratante(cpf, dataNascimento, emancipado)

        then:
        thrown(NullPointerException.class)
    }

    //TODO Testar inimputabilidade
    //TODO Testar maioridade
}
