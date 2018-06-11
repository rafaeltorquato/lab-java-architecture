package br.com.architecture.poc.javaee.persistence.mongo;

import br.com.architecture.poc.api.common.SSN;
import br.com.architecture.poc.api.loan.domain.Hirer;
import br.com.architecture.poc.api.loan.domain.HirerRepository;
import com.mongodb.BasicDBObject;
import org.bson.Document;

/**
 * @author Rafael Torquato
 */
public class HirerRepositoryMongo extends MongoTemplate implements HirerRepository {


    public HirerRepositoryMongo() {
        super("hirerCollection");
    }

    @Override
    public Hirer bySocialSecurityNumber(SSN ssn) {
        BasicDBObject query = new BasicDBObject();
        query.put("ssn", ssn.toString());

        Document document = collection().find(query).first();

        Hirer hirer = null;
        if (!document.isEmpty()) {
            hirer = new Hirer(
                    new SSN(document.getString("ssn")),
                    document.getDate("birthDate")
            );
            hirer.setDateOfDeath(document.getDate("dateOfDeath"));
        }

        return hirer;
    }
}
