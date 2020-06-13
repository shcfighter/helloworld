package com.ecit.mongodb; /**
 * Hello world!
 *
 */
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class App {

    public static void main(String args[]) {

        try {

            // To connect to mongodb server
            char[] password = new char[]{'h','1','2','3','4','5','6'};
            MongoCredential credential = MongoCredential.createCredential("shop_user", "vertx_shop", password);
            MongoClient mongoClient = new MongoClient(new ServerAddress("111.231.132.168", 27017),
                    Arrays.asList(credential));//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/mongodb/mongodb_java.html



            // Now connect to your databases
            MongoDatabase database = mongoClient.getDatabase("vertx_shop");

            System.out.println("Connect to database successfully!");
            System.out.println("MongoDatabase inof is : "+database.getName());
            // If MongoDB in secure mode, authentication is required.
            // boolean auth = db.authenticate(myUserName, myPassword);
            // System.out.println("Authentication: "+auth);

            //MongoCollection coll = database.getCollection("mycol");
            //System.out.println("Collection mycol selected successfully");
            MongoCollection<Document> collection = database.getCollection("test");

            Document document = new Document().append("title", "MongoDB Insert Demo")
                    .append("description","database")
                    .append("likes", 30)
                    .append("by", "yiibai point")
                    .append("url", "http://www.yiibai.com/mongodb/");

            collection.insertOne(document);

            collection.find().forEach(printBlock);

            System.out.println("Document inserted successfully");


            /*System.out.println("当前数据库中的所有集合是：");

            for (String name : database.listCollectionNames()) {
                System.out.println(name);
            }*/




        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    static Block<Document> printBlock = new Block<Document>() {
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };


}

