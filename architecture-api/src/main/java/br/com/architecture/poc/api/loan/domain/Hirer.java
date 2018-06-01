package br.com.architecture.poc.api.loan.domain;

import br.com.architecture.poc.api.common.SocialSecurityNumber;
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
@EqualsAndHashCode(of = "socialSecurityNumber")
public class Hirer implements Serializable {

    @Getter
    private final SocialSecurityNumber socialSecurityNumber;
    private final Date dataNascimento;
    private final Boolean emancipado;
    @Setter
    private Date dataMorte;

    public Hirer(SocialSecurityNumber socialSecurityNumber, Date dataNascimento, Boolean emancipado) {
        this.socialSecurityNumber = Objects.requireNonNull(socialSecurityNumber);
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
