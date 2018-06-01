package br.com.architecture.poc.javaee.persistence.stubs;

import br.com.architecture.poc.api.common.SocialSecurityNumber;
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

    private Map<SocialSecurityNumber, Hirer> contratantes = new HashMap<>();

    @PostConstruct
    public void criarMocks() {
        try {
            SimpleDateFormat ddMMyyyy = new SimpleDateFormat("ddMMyyyy");
            Hirer hirer1 = new Hirer(
                    new SocialSecurityNumber("99198720163"),
                    ddMMyyyy.parse("07101982"),
                    false
            );
            contratantes.put(hirer1.getSocialSecurityNumber(), hirer1);

            Hirer hirer2 = new Hirer(
                    new SocialSecurityNumber("99476239042"),
                    ddMMyyyy.parse("01012000"),
                    false
            );
            hirer2.setDataMorte(ddMMyyyy.parse("01012018"));
            contratantes.put(hirer2.getSocialSecurityNumber(), hirer2);

            Hirer hirer3 = new Hirer(
                    new SocialSecurityNumber("66529623060"),
                    ddMMyyyy.parse("01012005"),
                    true
            );
            contratantes.put(hirer3.getSocialSecurityNumber(), hirer3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Hirer bySocialSecurityNumber(SocialSecurityNumber socialSecurityNumber) {
        return contratantes.get(socialSecurityNumber);
    }
}
