package br.com.architecture.poc.javaee.persistence.jpa;


import br.com.architecture.poc.api.common.SSN;
import br.com.architecture.poc.api.loan.domain.Hirer;
import br.com.architecture.poc.api.loan.domain.HirerRepository;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * @author Rafael Torquato
 */
@Singleton
public class HirerRepositoryJpa implements HirerRepository {

    @PersistenceContext(unitName = "architecture-poc")
    private EntityManager em;

    @Override
    public Optional<Hirer> bySocialSecurityNumber(SSN ssn) {
        Hirer hirer = null;
        try {
            HirerEntity entity = em.find(HirerEntity.class, ssn.toString());
            if (entity != null) {
                hirer = new Hirer(
                        ssn,
                        entity.getBirthDate()
                );
                hirer.setDateOfDeath(entity.getDateOfDeath());
            }
        } catch (NoResultException ignored) {
        }
        return Optional.ofNullable(hirer);
    }
}
