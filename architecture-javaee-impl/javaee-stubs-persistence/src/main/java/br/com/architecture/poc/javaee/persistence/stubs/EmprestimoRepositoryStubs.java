package br.com.architecture.poc.javaee.persistence.stubs;

import br.com.architecture.poc.api.domain.Contratante;
import br.com.architecture.poc.api.domain.Emprestimo;
import br.com.architecture.poc.api.domain.EmprestimoRepository;

import javax.ejb.Singleton;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Rafael Torquato
 */
@Singleton
public class EmprestimoRepositoryStubs implements EmprestimoRepository {

    private Map<UUID, Emprestimo> emprestimos = new ConcurrentHashMap<>();

    @Override
    public void armazenar(Emprestimo emprestimo) {
        emprestimos.putIfAbsent(emprestimo.getIdentificador(), emprestimo);
    }

    @Override
    public List<Emprestimo> porContratante(Contratante contratante) {
        return emprestimos.values().stream()
                .filter(e -> e.getContratante().equals(contratante))
                .collect(Collectors.toList());
    }
}
