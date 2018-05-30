package br.coop.cooperforte.architecture.api.impl.jpa;

import br.coop.cooperforte.architecture.api.comum.Moeda;
import br.coop.cooperforte.architecture.api.comum.Valor;
import br.coop.cooperforte.architecture.api.dominio.Contratante;
import br.coop.cooperforte.architecture.api.dominio.Emprestimo;
import br.coop.cooperforte.architecture.api.dominio.EmprestimoRepository;
import br.coop.cooperforte.architecture.api.dominio.Parcelas;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rafael Torquato (Mirante Tecnologia)
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
        List<EmprestimoEntity> lista = em.createQuery("select e from Emprestimo e where e.cpf = :cpf ")
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
