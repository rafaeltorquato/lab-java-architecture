package br.com.architecture.poc.javaee.persistence.jpa;


import br.com.architecture.poc.api.common.SSN;
import br.com.architecture.poc.api.loan.domain.Hirer;
import br.com.architecture.poc.api.loan.domain.HirerRepository;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Rafael Torquato
 */
@Singleton
public class HirerRepositoryJpa implements HirerRepository {

    @PersistenceContext(unitName = "architecture-poc")
    private EntityManager em;

    @Override
    public Hirer bySocialSecurityNumber(SSN SSN) {
        ContratanteEntity entidade = em.find(ContratanteEntity.class, SSN.toString());
        Hirer hirer = null;
        if (entidade != null) {
            hirer = new Hirer(
                    SSN,
                    entidade.getDataNascimento()
            );
            hirer.setDateOfDeath(entidade.getDataMorte());
        }
        return hirer;
    }
}
