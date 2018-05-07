package com.offershopper.searchservice.controllerlogic;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.offershopper.searchservice.model.AddressBean;
import com.offershopper.searchservice.model.OfferBean;
import com.offershopper.searchservice.spellcheck.SpellCheck;

public class SearchLogic {
  
  
//@Autowired
OfferBean offer;

MongoClient mongoClient;
MongoDatabase database;
MongoCollection<Document> collection;
Boolean caseSensitive = false;
Boolean diacriticSensitive = false;
  
  public ResponseEntity<List<OfferBean>> getSearchResults(String searchKey) {
    searchKey.toLowerCase();
    try {
      searchKey = SpellCheck.spellChecker(searchKey);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
    mongoLogger.setLevel(Level.SEVERE);

    List<OfferBean> searchResults = new ArrayList<OfferBean>();

    mongoClient = new MongoClient(new MongoClientURI("mongodb://10.151.61.153:27017"));
    database = mongoClient.getDatabase("offershopperdb");
    collection = database.getCollection("offers");

    collection.createIndex(new Document("offerCategories", "text").append("offerTitle", "text").append("keywords", "text"),
        new IndexOptions());

    try {

      MongoCursor<Document> cursor = null;
      
      
      
      Document findCommand = new Document("$text",
          new Document("$search", searchKey).append("$caseSensitive", new Boolean(caseSensitive))
          .append("$diacriticSensitive", new Boolean(diacriticSensitive)));

      Document projectCommand =  new Document(
          "score", new Document("$meta", "textScore"));
      

      Document sortCommand = new Document(
          "score", new Document("$meta", "textScore"));

      
      
      cursor = collection 
          .find(findCommand)
          .projection(projectCommand)
          .sort(sortCommand)
          .iterator();


      
      while (cursor.hasNext()) {
        Document article = cursor.next();
        System.out.println(article.toString());
        offer = new OfferBean();
        offer.setDiscount(article.getDouble("discount").floatValue());
        System.out.println("1");
        

        
        offer.setOfferId(article.get("_id").toString());
        System.out.println("2");
        offer.setDateOfAnnouncement(article.getDate("dateOfAnnouncement").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        System.out.println("3");
        offer.setOfferCategory(article.getString("offerCategories"));
        System.out.println("4");
        offer.setOfferDescription(article.getString("offerDescription"));
        System.out.println("5");
        offer.setOfferRating(article.getDouble("offerRating").floatValue());
        System.out.println("6");
        offer.setOfferTerms(article.getString("offerTerms"));
        System.out.println("7");
        offer.setOfferTitle(article.getString("offerTitle"));
        System.out.println("8");
        offer.setOfferValidity(article.getDate("offerValidity").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        System.out.println("9");
        offer.setOriginalPrice(article.getDouble("originalPrice").floatValue());
        System.out.println("10");
        offer.setUserId((String) article.get("userId"));
        System.out.println("11");
        Document address = (Document) article.get("address");
        AddressBean addressBean = new AddressBean();
        addressBean.setName(address.getString("name"));
        System.out.println("12");
        addressBean.setCity(address.getString("city"));
        System.out.println("13");
        addressBean.setCity(address.getString("street"));
        System.out.println("14");
        addressBean.setState(address.getString("state"));
        System.out.println("15");
        addressBean.setZipCode(address.getInteger("zipCode"));
        System.out.println("16");
        offer.setAddress(addressBean);
        System.out.println(offer);
        System.out.println("here-2"+offer.toString());
        searchResults.add(offer);
      }

      cursor.close();
      // return ResponseEntity.status(HttpStatus.FOUND).body(searchResults);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      collection.dropIndexes();
      mongoClient.close();
    }
    return ResponseEntity.status(HttpStatus.OK).body(searchResults);
  }
  
  
  
  public ResponseEntity<List<Document>> searchByofferCategoriesAndSearchkey(String offerCategories,
      String searchKey) {
    searchKey.toLowerCase();
   try {
      searchKey = SpellCheck.spellChecker(searchKey);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
    mongoLogger.setLevel(Level.SEVERE);

    List<Document> searchResults = new ArrayList<Document>();

    mongoClient = new MongoClient(new MongoClientURI("mongodb://10.151.61.153:27017"));
    database = mongoClient.getDatabase("offershopperdb");
    collection = database.getCollection("offers");

    collection.createIndex(new Document("offerCategories", "text").append("offerTitle", "text").append("keywords", "text"),
        new IndexOptions());

    try {

      MongoCursor<Document> cursor = null;


      
      Document findCommand = new Document("$text",
          new Document("$search", searchKey).append("$caseSensitive", new Boolean(caseSensitive))
          .append("$diacriticSensitive", new Boolean(diacriticSensitive)))
          .append("offerCategories", offerCategories);

      Document projectCommand =  new Document(
          "score", new Document("$meta", "textScore"));
      

      Document sortCommand = new Document(
          "score", new Document("$meta", "textScore")
      );

      
      
      cursor = collection 
          .find(findCommand)
          .projection(projectCommand)
          .sort(sortCommand)
          .iterator();
      
      


      while (cursor.hasNext()) {
        Document article = cursor.next();
        System.out.println(article);
        searchResults.add(article);
      }

      cursor.close();
      // return ResponseEntity.status(HttpStatus.FOUND).body(searchResults);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      collection.dropIndexes();
      mongoClient.close();
      return ResponseEntity.status(HttpStatus.OK).body(searchResults);
    }

  }
  
  public ResponseEntity<List<Document>> searchByofferCategories(String offerCategories) {

    Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
    mongoLogger.setLevel(Level.SEVERE);

    List<Document> searchResults = new ArrayList<Document>();

    mongoClient = new MongoClient(new MongoClientURI("mongodb://10.151.61.153:27017"));
    database = mongoClient.getDatabase("offershopperdb");
    collection = database.getCollection("offers");

    collection.createIndex(new Document("offerCategories", "text"), new IndexOptions());

    try {

      MongoCursor<Document> cursor = null;
      
      
      Document findCommand = new Document("$text",
          new Document("$search", offerCategories).append("$caseSensitive", new Boolean(caseSensitive))
          .append("$diacriticSensitive", new Boolean(diacriticSensitive)));

      Document projectCommand =  new Document(
          "score", new Document("$meta", "textScore"));
      

      Document sortCommand = new Document(
          "score", new Document("$meta", "textScore")
      );

      
      
      cursor = collection 
          .find(findCommand)
          .projection(projectCommand)
          .sort(sortCommand)
          .iterator();


      while (cursor.hasNext()) {
        Document article = cursor.next();
        System.out.println(article);
        System.out.println("here-2");
        searchResults.add(article);
      }

      cursor.close();
      // return ResponseEntity.status(HttpStatus.FOUND).body(searchResults);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      collection.dropIndexes();
      mongoClient.close();
      return ResponseEntity.status(HttpStatus.OK).body(searchResults);
    }

  }
  
  
}
