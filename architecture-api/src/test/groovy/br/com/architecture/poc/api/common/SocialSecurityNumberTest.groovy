package br.com.architecture.poc.api.common

import spock.lang.Specification

/**
 * @author Rafael Torquato
 */
class SocialSecurityNumberTest extends Specification {


    def "deve falhar ao criar CPF com menos de 11 digitos"() {
        given:
        def cpfString = '9919872016'

        when:
        new SocialSecurityNumber(cpfString)

        then:
        thrown(IllegalArgumentException.class)
    }

    def "deve falhar ao criar CPF valor null"() {
        given:
        def cpfString = null

        when:
        new SocialSecurityNumber(cpfString)

        then:
        thrown(NullPointerException.class)
    }

    def "deve formatar corretamente um cpf"() {
        given:
        def cpfString = '99198720163'

        when:
        def formatado = new SocialSecurityNumber(cpfString).formatado()

        then:
        formatado == '991.987.201-63'
    }

}
