package br.coop.cooperforte.architecture.api.comum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Rafael Torquato (Mirante Tecnologia)
 */
@Getter
@RequiredArgsConstructor
public class EmprestimoException extends Exception {
    private final MensagemErro mensagemErro;
}
