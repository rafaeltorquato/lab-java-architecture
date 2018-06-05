package br.com.architecture.poc.javaee.persistence.mongo;

import br.coop.cooperforte.architecture.api.comum.Moeda;
import br.coop.cooperforte.architecture.api.comum.Valor;
import br.coop.cooperforte.architecture.api.dominio.Contratante;
import br.coop.cooperforte.architecture.api.dominio.Emprestimo;
import br.coop.cooperforte.architecture.api.dominio.EmprestimoRepository;
import br.coop.cooperforte.architecture.api.dominio.Parcelas;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import org.bson.Document;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafael Torquato
 */
@Singleton
public class EmprestimoRepositoryMongo extends MongoTemplate implements EmprestimoRepository {

    public EmprestimoRepositoryMongo() {
        super("emprestimos");
    }

    @Override
    public void armazenar(Emprestimo emprestimo) {
        Document document = new Document();
        document.put("identificador", emprestimo.getIdentificador().toString());
        document.put("value", emprestimo.getValor().doubleValue());
        document.put("currency", emprestimo.getValor().moeda().toString());
        document.put("loanInstallmentQuantity", emprestimo.getParcelas());
        document.put("hirerSsn", emprestimo.getContratante().getCpf().toString());
        document.put("dataContratacao", emprestimo.getDataContratacao());
        collection().insertOne(document);
    }

    @Override
    public List<Emprestimo> porContratante(Contratante contratante) {
        List<Emprestimo> emprestimos = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        query.put("hirerSsn", contratante.getCpf().toString());
        collection().find(query)
                .forEach((Block<Document>) d -> {
                    emprestimos.add(new Emprestimo(
                            new Valor(d.getDouble("value"), Moeda.valueOf(d.getString("currency"))),
                            new Parcelas(d.getInteger("loanInstallmentQuantity")),
                            contratante
                    ));
                });

        return emprestimos;
    }
}
