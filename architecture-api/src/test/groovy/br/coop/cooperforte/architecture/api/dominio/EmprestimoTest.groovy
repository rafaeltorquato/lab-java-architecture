package br.coop.cooperforte.architecture.api.dominio

import br.coop.cooperforte.architecture.api.comum.CPF
import br.coop.cooperforte.architecture.api.comum.Moeda
import br.coop.cooperforte.architecture.api.comum.Valor
import spock.lang.Specification

/**
 * @author Rafael Torquato (Mirante Tecnologia)
 */
class EmprestimoTest extends Specification {

    def "deve falhar ao criar Emprestimo com valor null"() {
        given:
        def valor = null
        def parcelas = new Parcelas(10)
        def contratante = new Contratante(
                new CPF("99198720163"),
                new Date(),
                true
        )

        when:
        new Emprestimo(valor, parcelas, contratante)

        then:
        thrown(NullPointerException.class)
    }

    def "deve falhar ao criar Emprestimo com parcelas null"() {
        given:
        def valor = new Valor(10000.00, Moeda.REAL)
        def parcelas = null
        def contratante = new Contratante(
                new CPF("99198720163"),
                new Date(),
                true
        )

        when:
        new Emprestimo(valor, parcelas, contratante)

        then:
        thrown(NullPointerException.class)
    }

    def "deve falhar ao criar Emprestimo com contratante null"() {
        given:
        def valor = new Valor(10000.00, Moeda.REAL)
        def parcelas = null
        def contratante = null

        when:
        new Emprestimo(valor, parcelas, contratante)

        then:
        thrown(NullPointerException.class)
    }
}
