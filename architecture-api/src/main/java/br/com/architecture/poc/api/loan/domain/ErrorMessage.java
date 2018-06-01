package br.com.architecture.poc.api.loan.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Rafael Torquato
 */
@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

    HIRER_DOES_NOT_EXIST("0001", "Hirer does not exist."),
    HIRER_IS_DEAD("0001", "Hirer is dead.");

    private final String code;
    private final String description;

}
