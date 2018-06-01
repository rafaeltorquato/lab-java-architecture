package br.com.architecture.poc.api.loan.domain

import br.com.architecture.poc.api.common.Currency
import spock.lang.Specification

/**
 * @author Rafael Torquato
 */
class HireLoanTest extends Specification {

    def "Should fail beacause hirer does not exist"() {
        given:
        def contratanteRepository = Mock(HirerRepository)
        contratanteRepository.bySocialSecurityNumber(_) >> { null }

        def emprestimoRepository = Mock(LoanRepository)
        emprestimoRepository.store(_) >> {}

        def solicitacao = new HireLoan.Request()
        solicitacao.cpf = "99198720163"
        solicitacao.valor = 1000.00D
        solicitacao.moeda = Currency.USD.toString()
        solicitacao.quantidadeParcelas = 10

        def contratarEmprestimo = new HireLoan(
                contratanteRepository,
                emprestimoRepository
        )
        when:
        contratarEmprestimo.executar(solicitacao)

        then:
        def e = thrown(LoanException.class)
        e.errorMessage == ErrorMessage.HIRER_DOES_NOT_EXIST
        1 * contratanteRepository.bySocialSecurityNumber(_)
        0 * emprestimoRepository.store(_)
    }

}
