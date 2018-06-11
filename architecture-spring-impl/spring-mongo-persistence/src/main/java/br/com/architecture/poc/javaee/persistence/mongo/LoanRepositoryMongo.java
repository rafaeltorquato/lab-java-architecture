package br.com.architecture.poc.javaee.persistence.mongo;

import br.com.architecture.poc.api.common.Currency;
import br.com.architecture.poc.api.common.MoneyValue;
import br.com.architecture.poc.api.loan.domain.Hirer;
import br.com.architecture.poc.api.loan.domain.Loan;
import br.com.architecture.poc.api.loan.domain.LoanInstallment;
import br.com.architecture.poc.api.loan.domain.LoanRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafael Torquato
 */
public class LoanRepositoryMongo extends MongoTemplate implements LoanRepository {

    public LoanRepositoryMongo() {
        super("loanCollection");
    }

    @Override
    public void store(Loan loan) {
        Document document = new Document();
        document.put("identifier", loan.getIdentifier().toString());
        document.put("value", loan.getMoneyValue().doubleValue());
        document.put("currency", loan.getMoneyValue().currency().toString());
        document.put("loanInstallmentQuantity", loan.getLoanInstallment().intValue());
        document.put("hirerSsn", loan.getHirer().getSsn().toString());
        document.put("contractDate", loan.getContractDate());
        collection().insertOne(document);
    }

    @Override
    public List<Loan> loansOfHirer(Hirer hirer) {
        List<Loan> loans = new ArrayList<>();

        BasicDBObject query = new BasicDBObject();
        query.put("hirerSsn", hirer.getSsn().toString());
        collection().find(query)
                .forEach((Block<Document>) d -> loans.add(new Loan(
                        new MoneyValue(d.getDouble("value"), Currency.valueOf(d.getString("currency"))),
                        new LoanInstallment(d.getInteger("loanInstallmentQuantity")),
                        hirer
                )));
        return loans;
    }
}
