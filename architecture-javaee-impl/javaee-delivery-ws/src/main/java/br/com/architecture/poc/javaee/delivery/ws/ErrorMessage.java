package br.com.architecture.poc.javaee.delivery.ws;

import br.com.architecture.poc.api.domain.EmprestimoException;
import lombok.Data;

@Data
public class ErrorMessage {
    private String msg;

    public ErrorMessage(EmprestimoException e) {
        msg = e.getMensagemErro().getDescricao();
    }
}
