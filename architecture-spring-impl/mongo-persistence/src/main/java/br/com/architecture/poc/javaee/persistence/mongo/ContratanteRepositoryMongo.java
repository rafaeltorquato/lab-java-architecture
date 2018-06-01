package br.com.architecture.poc.javaee.persistence.mongo;

import br.coop.cooperforte.architecture.api.comum.CPF;
import br.coop.cooperforte.architecture.api.dominio.Contratante;
import br.coop.cooperforte.architecture.api.dominio.ContratanteRepository;
import com.mongodb.BasicDBObject;
import org.bson.Document;

import javax.ejb.Singleton;

/**
 * @author Rafael Torquato
 */
@Singleton
public class ContratanteRepositoryMongo extends MongoTemplate implements ContratanteRepository {


    public ContratanteRepositoryMongo() {
        super("contratantes");
    }

    @Override
    public Contratante porCPF(CPF cpf) {
        BasicDBObject query = new BasicDBObject();
        query.put("cpf", cpf.toString());

        Document document = collection().find(query).first();

        Contratante contratante = null;
        if (!document.isEmpty()) {
            contratante = new Contratante(
                    new CPF(document.getString("cpf")),
                    document.getDate("dataNascimento"),
                    document.getBoolean("emancipado")
            );
            contratante.setDataMorte(document.getDate("dateOfDeath"));
        }

        return contratante;
    }
}
