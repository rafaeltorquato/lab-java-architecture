package br.coop.cooperforte.architecture.api.comum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Rafael Torquato (Mirante Tecnologia)
 */
@Getter
@RequiredArgsConstructor
public enum MensagemErro {
    CONTRATANTE_INIMPUTAVEL("0001", "Contratante inimput\u00E1vel."),
    CONTRATANTE_INEXISTENTE("0002", "Contratante inexistente."),
    CONTRATANTE_MORTO("0003", "Contratante morto.");

    private final String codigo;
    private final String descricao;
}
