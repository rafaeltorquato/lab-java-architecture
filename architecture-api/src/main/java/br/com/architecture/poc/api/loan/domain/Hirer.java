package br.com.architecture.poc.api.loan.domain;

import br.com.architecture.poc.api.common.SocialSecurityNumber;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
    private final Date birthDate;
    @Setter
    private Date dateOfDeath;

    public Hirer(SocialSecurityNumber socialSecurityNumber, Date birthDate) {
        this.socialSecurityNumber = Objects.requireNonNull(socialSecurityNumber);
        this.birthDate = Objects.requireNonNull(birthDate);
    }

    public boolean dead() {
        return dateOfDeath != null;
    }

    public Optional<Date> getDateOfDeath() {
        return Optional.ofNullable(dateOfDeath);
    }

}
