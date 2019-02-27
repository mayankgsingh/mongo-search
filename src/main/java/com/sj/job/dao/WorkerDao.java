package com.sj.job.dao;

import static com.mongodb.client.model.Filters.eq;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * DAO class to Worker collection.
 * 
 * @author Mayank
 *
 */
@Repository
@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WorkerDao {
  
  @Autowired
  private MongoDatabase jobsDb;
  private MongoCollection<Document> worker;
  
  @PostConstruct
  public void init() {
    worker = jobsDb.getCollection("worker");
  }
  
  public Document getWorkderById(final Integer workerId) {
    return worker.find(eq("userId", workerId)).first();
  }
}
