package com.offershopper.searchservice.spellcheck;

import java.io.IOException;
import java.util.List;

import org.bson.Document;
import org.languagetool.JLanguageTool;
import org.languagetool.language.BritishEnglish;
import org.languagetool.rules.RuleMatch;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.offershopper.searchservice.soundex.Soundex;

public class SpellCheck {

  public static String spellChecker(String input) throws IOException {
    
    //open source library for java to check spelling errors
    JLanguageTool langTool = new JLanguageTool(new BritishEnglish());
    
    //connection made to soundex collection of mongoDB
    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://10.151.61.153:27017"));
    MongoDatabase database = mongoClient.getDatabase("offershopperdb");
    MongoCollection<Document> collection = database.getCollection("soundex");
    
    MongoCursor cursor;
    String output="";
    output=input;
    String replacement="";
    List<RuleMatch> matches = langTool.check(input);
    try {
    for (RuleMatch match : matches) {
      replacement="";
      String target="";
      target=input.substring(match.getFromPos(), match.getToPos());
      String soundexCode = Soundex.getGode(input.substring(match.getFromPos(), match.getToPos()));
      cursor = collection.find(new Document("code", soundexCode)).iterator();
      if(!cursor.hasNext())
        replacement=input.substring(match.getFromPos(), match.getToPos())+" ";
      while (cursor.hasNext()) {
        Document article = (Document) cursor.next();
        replacement=replacement+article.get("word")+" ";
        }
      System.out.println("\n\n"+replacement+"\n\n");
      output=output.replace(target,replacement);
    }
} catch (Exception e) {
  e.printStackTrace();
} finally {
  collection.dropIndexes();
  mongoClient.close();
}
System.out.println(output);
return output;
  }
}
