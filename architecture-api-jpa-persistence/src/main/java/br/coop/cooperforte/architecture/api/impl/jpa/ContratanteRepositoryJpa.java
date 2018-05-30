package br.coop.cooperforte.architecture.api.impl.jpa;

import br.coop.cooperforte.architecture.api.comum.CPF;
import br.coop.cooperforte.architecture.api.dominio.Contratante;
import br.coop.cooperforte.architecture.api.dominio.ContratanteRepository;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Rafael Torquato (Mirante Tecnologia)
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
