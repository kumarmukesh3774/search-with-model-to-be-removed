package com.offershopper.searchservice.controller;

import java.util.HashSet;
import java.util.Set;

import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.offershopper.searchservice.soundex.Soundex;
@CrossOrigin
@RestController
public class SoundexLibraryController {

  @PostMapping("/add-code")
  public HashSet<String> insert(@RequestBody Document document) {
    // establishing connection
    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://10.151.61.153:27017"));
    MongoDatabase database = mongoClient.getDatabase("offershopperdb");
    MongoCollection<Document> collection = database.getCollection("soundex");
    MongoCursor cursor ;
    
    HashSet<String> set = new HashSet<String>();
    String title = (String) document.get("offerTitle");
    String keywords = (String) document.get("keywords");
    String offerCategories = (String) document.get("offerCategories");
    set.add(offerCategories.toLowerCase().trim());
    //index
    //collection.createIndex(new Document("word", 1), new IndexOptions().unique(true));  
    String regx="[,\\s]+";
    
    String[] offerCategoriesSplit = offerCategories.split(regx);
    for (String str : offerCategoriesSplit) {
      set.add(str.toLowerCase().trim());
      
    }
    
    String[] titleSplit = title.split(regx);
    for (String str : titleSplit) {
      set.add(str.toLowerCase().trim());
      
    }
    String[] keywordsSplit = keywords.split(regx);
    for (String str : keywordsSplit) {
      set.add(str.toLowerCase().trim());
    }
    
    
    try {
      for (String str : set) {
        System.out.println(Soundex.getGode(str));
        cursor = collection.find(new Document("word",str)).iterator();
       if(cursor.hasNext()) { 
         cursor.close();
         continue;
       }
          cursor.close();
          collection.insertOne(new Document("code", Soundex.getGode(str)).append("word", str));
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      collection.dropIndexes();
      mongoClient.close();
    }
    return set;
  }

}
