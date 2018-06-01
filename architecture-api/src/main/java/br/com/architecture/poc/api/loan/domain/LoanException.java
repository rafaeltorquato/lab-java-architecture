package br.com.architecture.poc.api.loan.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Rafael Torquato
 */
@Getter
@RequiredArgsConstructor
public class LoanException extends Exception {
    private final ErrorMessage errorMessage;
}
