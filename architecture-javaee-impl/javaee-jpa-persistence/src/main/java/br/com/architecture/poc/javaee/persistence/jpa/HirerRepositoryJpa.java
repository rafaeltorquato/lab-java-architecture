package br.com.architecture.poc.javaee.persistence.jpa;


import br.com.architecture.poc.api.common.SocialSecurityNumber;
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
    public Hirer bySocialSecurityNumber(SocialSecurityNumber socialSecurityNumber) {
        ContratanteEntity entidade = em.find(ContratanteEntity.class, socialSecurityNumber.toString());
        Hirer hirer = null;
        if (entidade != null) {
            hirer = new Hirer(
                    socialSecurityNumber,
                    entidade.getDataNascimento(),
                    entidade.getEmancipado()
            );
            hirer.setDataMorte(entidade.getDataMorte());
        }
        return hirer;
    }
}
