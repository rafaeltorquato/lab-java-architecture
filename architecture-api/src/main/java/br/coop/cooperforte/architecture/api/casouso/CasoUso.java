package br.coop.cooperforte.architecture.api.casouso;

import br.coop.cooperforte.architecture.api.comum.EmprestimoException;

/**
 * @author Rafael Torquato (Mirante Tecnologia)
 */
public interface CasoUso<E, S> {
    S executar(E entrada) throws EmprestimoException;
}
