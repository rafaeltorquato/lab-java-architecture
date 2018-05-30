package br.coop.cooperforte.architecture.api.dominio;

import br.coop.cooperforte.architecture.api.comum.CPF;

/**
 * @author Rafael Torquato (Mirante Tecnologia)
 */
public interface ContratanteRepository {
    Contratante porCPF(CPF cpf);
}
