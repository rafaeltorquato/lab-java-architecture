package br.com.architecture.poc.javaee.persistence.jpa;

import br.coop.cooperforte.architecture.api.dominio.Emprestimo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @author Rafael Torquato
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class EmprestimoEntity implements Serializable {

    @Id
    private UUID identificador;
    private String cpf;
    private BigDecimal valor;
    private Integer quantidadeParcelas;
    private String moeda;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataContratacao;

    public EmprestimoEntity(Emprestimo emprestimo) {
        cpf = emprestimo.getContratante().getCpf().toString();
        identificador = emprestimo.getIdentificador();
        dataContratacao = emprestimo.getDataContratacao();
        quantidadeParcelas = emprestimo.getParcelas().intValue();
        valor = new BigDecimal(emprestimo.getValor().doubleValue());
        moeda = emprestimo.getValor().moeda().toString();
    }
}
