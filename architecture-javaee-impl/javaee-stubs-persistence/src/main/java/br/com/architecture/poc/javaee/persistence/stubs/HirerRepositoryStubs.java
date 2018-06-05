package br.com.architecture.poc.javaee.persistence.stubs;

import br.com.architecture.poc.api.common.SSN;
import br.com.architecture.poc.api.loan.domain.Hirer;
import br.com.architecture.poc.api.loan.domain.HirerRepository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rafael Torquato
 */
@Singleton
public class HirerRepositoryStubs implements HirerRepository {

    private Map<SSN, Hirer> contratantes = new HashMap<>();

    @PostConstruct
    public void criarMocks() {
        try {
            SimpleDateFormat ddMMyyyy = new SimpleDateFormat("ddMMyyyy");
            Hirer hirer1 = new Hirer(
                    new SSN("99198720163"),
                    ddMMyyyy.parse("07101982")
            );
            contratantes.put(hirer1.getSSN(), hirer1);

            Hirer hirer2 = new Hirer(
                    new SSN("99476239042"),
                    ddMMyyyy.parse("01012000")
            );
            hirer2.setDateOfDeath(ddMMyyyy.parse("01012018"));
            contratantes.put(hirer2.getSSN(), hirer2);

            Hirer hirer3 = new Hirer(
                    new SSN("66529623060"),
                    ddMMyyyy.parse("01012005")
            );
            contratantes.put(hirer3.getSSN(), hirer3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Hirer bySocialSecurityNumber(SSN SSN) {
        return contratantes.get(SSN);
    }
}
