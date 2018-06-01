package br.com.architecture.poc.api.domain;

import java.util.List;

/**
 * @author Rafael Torquato
 */
public interface EmprestimoRepository {
    void armazenar(Emprestimo emprestimo);

    List<Emprestimo> porContratante(Contratante contratante);
}
