package br.coop.cooperforte.architecture.api.impl.stubs;

import br.coop.cooperforte.architecture.api.dominio.Contratante;
import br.coop.cooperforte.architecture.api.dominio.ContratanteRepository;
import br.coop.cooperforte.architecture.api.comum.CPF;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Rafael Torquato (Mirante Tecnologia)
 */
@Singleton
public class ContratanteRepositoryStubs implements ContratanteRepository {

    private Map<CPF, Contratante> contratantes = new HashMap<>();

    @PostConstruct
    public void criarMocks() {
        try {
            SimpleDateFormat ddMMyyyy = new SimpleDateFormat("ddMMyyyy");
            Contratante contratante1 = new Contratante(
                    new CPF("99198720163"),
                    ddMMyyyy.parse("07101982"),
                    false
            );
            contratantes.put(contratante1.getCpf(), contratante1);

            Contratante contratante2 = new Contratante(
                    new CPF("99476239042"),
                    ddMMyyyy.parse("01012000"),
                    false
            );
            contratante2.setDataMorte(ddMMyyyy.parse("01012018"));
            contratantes.put(contratante2.getCpf(), contratante2);

            Contratante contratante3 = new Contratante(
                    new CPF("66529623060"),
                    ddMMyyyy.parse("01012005"),
                    true
            );
            contratantes.put(contratante3.getCpf(), contratante3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contratante porCPF(CPF cpf) {
        return contratantes.get(cpf);
    }
}
