package br.com.architecture.poc.javaee.persistence.jpa;

import br.com.architecture.poc.api.common.Currency;
import br.com.architecture.poc.api.common.MoneyValue;
import br.com.architecture.poc.api.loan.domain.Hirer;
import br.com.architecture.poc.api.loan.domain.Loan;
import br.com.architecture.poc.api.loan.domain.LoanRepository;
import br.com.architecture.poc.api.loan.domain.LoanInstallment;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rafael Torquato
 */
@Singleton
public class LoanRepositoryJpa implements LoanRepository {
    //FIXME Adicionar Producer
    @PersistenceContext(unitName = "architecture-poc")
    private EntityManager em;

    @Override
    public void store(Loan loan) {
        em.persist(new EmprestimoEntity(loan));
    }

    @Override
    public List<Loan> loansOfHirer(Hirer hirer) {
        List<EmprestimoEntity> lista = em.createQuery("select e from EmprestimoEntity e where e.cpf = :cpf ")
                .setParameter("cpf", hirer.getSSN().toString())
                .getResultList();
        return lista.stream()
                .map(e -> new Loan(
                        new MoneyValue(e.getValor().doubleValue(), Currency.valueOf(e.getMoeda())),
                        new LoanInstallment(e.getQuantidadeParcelas()),
                        hirer))
                .collect(Collectors.toList());
    }
}
