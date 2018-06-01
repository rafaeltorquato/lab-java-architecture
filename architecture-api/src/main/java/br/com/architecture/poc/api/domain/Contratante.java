package br.com.architecture.poc.api.domain;

import br.com.architecture.poc.api.common.CPF;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Rafael Torquato
 */
@EqualsAndHashCode(of = "cpf")
public class Contratante implements Serializable {

    @Getter
    private final CPF cpf;
    private final Date dataNascimento;
    private final Boolean emancipado;
    @Setter
    private Date dataMorte;

    public Contratante(CPF cpf, Date dataNascimento, Boolean emancipado) {
        this.cpf = Objects.requireNonNull(cpf);
        this.dataNascimento = Objects.requireNonNull(dataNascimento);
        this.emancipado = Objects.requireNonNull(emancipado);
    }

    public boolean imputavel(Integer maioridadePenal) {
        return !morto() && (emancipado || maiorDeIdade(maioridadePenal));
    }

    public boolean morto() {
        return dataMorte != null;
    }

    public Optional<Date> getDataMorte() {
        return Optional.ofNullable(dataMorte);
    }

    private boolean maiorDeIdade(Integer maioridadePenal) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -maioridadePenal);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime().after(dataNascimento);
    }
}
