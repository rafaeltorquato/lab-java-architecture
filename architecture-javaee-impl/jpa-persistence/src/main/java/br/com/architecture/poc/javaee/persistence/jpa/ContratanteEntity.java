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
public class ContratanteEntity implements Serializable {

    //TODO Contrutor com Contratante
    @Id
    private String cpf;
    private Date dataNascimento;
    private Date dataMorte;
    private Boolean emancipado;
}
