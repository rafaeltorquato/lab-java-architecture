package br.coop.cooperforte.architecture.api.impl.mongo;

import br.coop.cooperforte.architecture.api.comum.Moeda;
import br.coop.cooperforte.architecture.api.comum.Valor;
import br.coop.cooperforte.architecture.api.dominio.Contratante;
import br.coop.cooperforte.architecture.api.dominio.Emprestimo;
import br.coop.cooperforte.architecture.api.dominio.EmprestimoRepository;
import br.coop.cooperforte.architecture.api.dominio.Parcelas;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafael Torquato (Mirante Tecnologia)
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
        document.put("valor", emprestimo.getValor().doubleValue());
        document.put("moeda", emprestimo.getValor().moeda().toString());
        document.put("quantidadeParcelas", emprestimo.getParcelas());
        document.put("cpfContratante", emprestimo.getContratante().getCpf().toString());
        document.put("dataContratacao", emprestimo.getDataContratacao());
        collection().insertOne(document);
    }

    @Override
    public List<Emprestimo> porContratante(Contratante contratante) {
        List<Emprestimo> emprestimos = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        query.put("cpfContratante", contratante.getCpf().toString());
        collection().find(query)
                .forEach((Block<Document>) d -> {
                    emprestimos.add(new Emprestimo(
                            new Valor(d.getDouble("valor"), Moeda.valueOf(d.getString("moeda"))),
                            new Parcelas(d.getInteger("quantidadeParcelas")),
                            contratante
                    ));
                });

        return emprestimos;
    }
}
