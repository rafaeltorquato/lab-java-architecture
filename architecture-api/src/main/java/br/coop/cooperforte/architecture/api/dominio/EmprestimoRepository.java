package br.coop.cooperforte.architecture.api.dominio;

import java.util.List;

/**
 * @author Rafael Torquato (Mirante Tecnologia)
 */
public interface EmprestimoRepository {
    void armazenar(Emprestimo emprestimo);
    List<Emprestimo> porContratante(Contratante contratante);
}
