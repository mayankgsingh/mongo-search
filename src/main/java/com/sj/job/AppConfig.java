package com.sj.job;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

/**
 * Configuration class for application configs.
 * 
 * @author Mayank
 *
 */
@Configuration
@EnableAspectJAutoProxy
@PropertySources(@PropertySource("classpath:app.properties"))
public class AppConfig implements Closeable {
  private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);
  
  private MongoClient client;
  
  @Value( "${mongo.db.connection.url}" )
  private String connUrl;
  
  @Bean
  public MongoDatabase mongoDatabase() {
    if(client == null) {
      LOGGER.info("Connecting to MongoDB: {}", connUrl);
      MongoClientURI uri = new MongoClientURI(connUrl);
      client = new MongoClient(uri);
    }
    return client.getDatabase("jobsdb");
  }

  @Override
  public void close() throws IOException {
    if(client != null) {
      LOGGER.info("Closing MongoDB connections.");
      client.close();
    }
  }
  
  @Bean
  public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
