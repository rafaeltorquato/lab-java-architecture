package br.com.architecture.poc.api.domain;

import br.com.architecture.poc.api.common.CPF;

/**
 * @author Rafael Torquato
 */
public interface ContratanteRepository {
    Contratante porCPF(CPF cpf);
}
