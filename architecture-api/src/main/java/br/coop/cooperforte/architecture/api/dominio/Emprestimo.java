package br.coop.cooperforte.architecture.api.dominio;

import br.coop.cooperforte.architecture.api.comum.Valor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Rafael Torquato (Mirante Tecnologia)
 */
@Getter
@EqualsAndHashCode(of = "identificador")
public class Emprestimo implements Serializable {

    private final UUID identificador;
    private final Valor valor;
    private final Parcelas parcelas;
    private final Contratante contratante;
    private final Date dataContratacao;

    public Emprestimo(Valor valor, Parcelas parcelas, Contratante contratante) {
        this.identificador = UUID.randomUUID();
        this.valor = Objects.requireNonNull(valor);
        this.parcelas = Objects.requireNonNull(parcelas);
        this.contratante = Objects.requireNonNull(contratante);
        this.dataContratacao = new Date();
    }
}
