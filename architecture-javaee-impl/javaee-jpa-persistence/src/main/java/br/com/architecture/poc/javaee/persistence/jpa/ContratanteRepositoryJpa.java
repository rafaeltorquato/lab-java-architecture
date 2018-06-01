package br.com.architecture.poc.javaee.persistence.jpa;


import br.com.architecture.poc.api.common.CPF;
import br.com.architecture.poc.api.domain.Contratante;
import br.com.architecture.poc.api.domain.ContratanteRepository;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Rafael Torquato
 */
@Singleton
public class ContratanteRepositoryJpa implements ContratanteRepository {

    @PersistenceContext(unitName = "architecture-poc")
    private EntityManager em;

    @Override
    public Contratante porCPF(CPF cpf) {
        ContratanteEntity entidade = em.find(ContratanteEntity.class, cpf.toString());
        Contratante contratante = null;
        if (entidade != null) {
            contratante = new Contratante(
                    cpf,
                    entidade.getDataNascimento(),
                    entidade.getEmancipado()
            );
            contratante.setDataMorte(entidade.getDataMorte());
        }
        return contratante;
    }
}
