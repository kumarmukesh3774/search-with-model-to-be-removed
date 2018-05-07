package com.offershopper.searchservice;

import java.io.IOException;

import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
public class SearchServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SearchServiceApplication.class, args);
  }

  // for swagger
  @Bean
  public Docket api() throws IOException, XmlPullParserException {
    return new Docket(DocumentationType.SWAGGER_2);
  }

}
