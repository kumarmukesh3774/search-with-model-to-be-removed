package com.offershopper.searchservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.offershopper.searchservice.controllerlogic.TypeNextControllerLogic;

@CrossOrigin
@RestController
public class TypeNextSuggestionController {

  @GetMapping(value = "/q/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public static ResponseEntity<List<String>> typeNextSuggestionControllerDefault() {
    List<String> results = new ArrayList<String>();
    results.add("default");
    return ResponseEntity.status(HttpStatus.OK).body(results);

  }

  //to fetch data from redis database
  @GetMapping(value = "/q/{q}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public static ResponseEntity<Set<String>> typeNextSuggestionController(@PathVariable(value = "q") String q) {
    TypeNextControllerLogic typeNextControllerLogic= new TypeNextControllerLogic();
    return typeNextControllerLogic.typeNextSuggestionController(q);
  }
  //load data into redis database
  @PostMapping("/load-data")
  public void load_data(@RequestBody Document document) throws Exception {
    TypeNextControllerLogic typeNextControllerLogic=new TypeNextControllerLogic();
    typeNextControllerLogic.load_data(document);
  }
}
