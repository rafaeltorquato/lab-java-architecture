package br.coop.cooperforte.architecture.api.impl.mongo;

import br.coop.cooperforte.architecture.api.dominio.Contratante;
import br.coop.cooperforte.architecture.api.dominio.ContratanteRepository;
import br.coop.cooperforte.architecture.api.comum.CPF;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import javax.ejb.Singleton;

/**
 * @author Rafael Torquato (Mirante Tecnologia)
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
            contratante.setDataMorte(document.getDate("dataMorte"));
        }

        return contratante;
    }
}
