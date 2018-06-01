package br.com.architecture.poc.javaee.persistence.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

/**
 * @author Rafael Torquato
 */
abstract class MongoTemplate {

    private final MongoDatabase dataBase;
    private String collectionName;

    MongoTemplate(String collectionName) {
        this.collectionName = collectionName;
        MongoClient mongoClient = new MongoClient();
        dataBase = mongoClient.getDatabase("architecture");
    }

    MongoCollection<Document> collection() {
        boolean exists = dataBase.listCollectionNames().into(new ArrayList<>())
                .stream().anyMatch(s -> s.equals(collectionName));
        if (!exists) {
            dataBase.createCollection(collectionName);
        }
        return dataBase.getCollection(collectionName);
    }

}
