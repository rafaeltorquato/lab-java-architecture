package br.com.architecture.poc.javaee.persistence.jpa;

import br.com.architecture.poc.api.common.Moeda;
import br.com.architecture.poc.api.common.Valor;
import br.com.architecture.poc.api.domain.Contratante;
import br.com.architecture.poc.api.domain.Emprestimo;
import br.com.architecture.poc.api.domain.EmprestimoRepository;
import br.com.architecture.poc.api.domain.Parcelas;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rafael Torquato
 */
@Singleton
public class EmprestimoRepositoryJpa implements EmprestimoRepository {
    //FIXME Adicionar Producer
    @PersistenceContext(unitName = "architecture-poc")
    private EntityManager em;

    @Override
    public void armazenar(Emprestimo emprestimo) {
        em.persist(new EmprestimoEntity(emprestimo));
    }

    @Override
    public List<Emprestimo> porContratante(Contratante contratante) {
        List<EmprestimoEntity> lista = em.createQuery("select e from EmprestimoEntity e where e.cpf = :cpf ")
                .setParameter("cpf", contratante.getCpf().toString())
                .getResultList();
        return lista.stream()
                .map(e -> new Emprestimo(
                        new Valor(e.getValor().doubleValue(), Moeda.valueOf(e.getMoeda())),
                        new Parcelas(e.getQuantidadeParcelas()),
                        contratante))
                .collect(Collectors.toList());
    }
}
