package br.com.architecture.poc.api.common;

import br.com.architecture.poc.api.domain.EmprestimoException;

/**
 * @author Rafael Torquato
 */
public interface CasoUso<E, S> {
    S executar(E entrada) throws EmprestimoException;
}
