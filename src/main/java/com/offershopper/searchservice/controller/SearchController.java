package com.offershopper.searchservice.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.offershopper.searchservice.controllerlogic.SearchLogic;
import com.offershopper.searchservice.model.AddressBean;
import com.offershopper.searchservice.model.OfferBean;
import com.offershopper.searchservice.spellcheck.SpellCheck;
@CrossOrigin
@RestController
public class SearchController {

//  @Autowired
  OfferBean offer;
  
  MongoClient mongoClient;
  MongoDatabase database;
  MongoCollection<Document> collection;
  Boolean caseSensitive = false;
  Boolean diacriticSensitive = false;

  @GetMapping("/search-key/{searchKey}")
  public ResponseEntity<List<OfferBean>> getSearchResults(@PathVariable(value = "searchKey") String searchKey) {
    SearchLogic searchLogic= new SearchLogic();
    return searchLogic.getSearchResults(searchKey);
    
  }

  @GetMapping("offerCategories/{offerCategories}/search-key/{searchKey}")
  public ResponseEntity<List<Document>> searchByofferCategoriesAndSearchkey(@PathVariable(value = "offerCategories") String offerCategories,
      @PathVariable(value = "searchKey") String searchKey) {
    SearchLogic searchLogic= new SearchLogic();
    return searchLogic.searchByofferCategoriesAndSearchkey(offerCategories,searchKey);

  }

  @GetMapping("offerCategories/{offerCategories}")
  public ResponseEntity<List<Document>> searchByofferCategories(@PathVariable(value = "offerCategories") String offerCategories) {

    SearchLogic searchLogic= new SearchLogic();
    return searchLogic.searchByofferCategories(offerCategories);

  }
 
  
}
