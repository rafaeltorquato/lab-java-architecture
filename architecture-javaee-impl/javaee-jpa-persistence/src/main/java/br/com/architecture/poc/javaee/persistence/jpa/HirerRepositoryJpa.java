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
    public Hirer bySocialSecurityNumber(SSN ssn) {
        HirerEntity entity = em.find(HirerEntity.class, ssn.toString());
        Hirer hirer = null;
        if (entity != null) {
            hirer = new Hirer(
                    ssn,
                    entity.getBirthDate()
            );
            hirer.setDateOfDeath(entity.getDateOfDeath());
        }
        return hirer;
    }
}
