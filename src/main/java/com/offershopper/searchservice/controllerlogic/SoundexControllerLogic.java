package com.offershopper.searchservice.controllerlogic;

import java.util.HashSet;

import org.bson.Document;
import org.springframework.web.bind.annotation.RequestBody;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.offershopper.searchservice.controller.SoundexLibraryController;
import com.offershopper.searchservice.soundex.Soundex;

public class SoundexControllerLogic {
  
  public HashSet<String> insert(Document document) {
    SoundexLibraryController soundexLibraryController=new SoundexLibraryController();
    return soundexLibraryController.insert(document);
  
  }

}
