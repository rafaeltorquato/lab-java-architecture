package br.com.architecture.poc.javaee.delivery.ws;

import br.com.architecture.poc.api.loan.domain.LoanException;
import lombok.Data;

@Data
public class ErrorMessage {
    private String msg;

    public ErrorMessage(LoanException e) {
        msg = e.getErrorMessage().getDescription();
    }
}
