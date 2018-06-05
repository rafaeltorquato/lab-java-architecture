package br.com.architecture.poc.javaee.persistence.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Rafael Torquato
 */
@Setter
@Getter
@Entity
public class HirerEntity implements Serializable {

    @Id
    private String ssn;
    private Date birthDate;
    private Date dateOfDeath;

}
