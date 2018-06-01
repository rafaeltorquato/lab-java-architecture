package br.com.architecture.poc.api.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Rafael Torquato
 */
@Getter
@RequiredArgsConstructor
public class EmprestimoException extends Exception {
    private final MensagemErro mensagemErro;
}
