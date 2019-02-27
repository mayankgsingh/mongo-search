package com.ms.job.dao;

import static com.mongodb.client.model.Aggregates.addFields;
import static com.mongodb.client.model.Aggregates.limit;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Field;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

/**
 * DAO Class to access Job collection.
 * 
 * @author Mayank
 *
 */
@Repository
@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JobDao {

  @Autowired
  private MongoDatabase             jobsDb;
  private MongoCollection<Document> jobs;

  /**
   * Jobs suggested based on Worker Certificate and DriverLicense.
   * 
   * @param worker
   * @param limitResult
   * @return List<Document>
   */
  public List<Document> suggestJobs(final Document worker, final Integer limitResult) {
    List<Document> docs = new LinkedList<Document>();
    @SuppressWarnings("unchecked")
    List<String> certs = worker.get("certificates", List.class);
    boolean hasDrivingLicense = worker.getBoolean("hasDriversLicense");

    BasicDBList intersectionList = new BasicDBList();
    intersectionList.add("$requiredCertificates");
    intersectionList.add(certs);

    DBObject intersection = new BasicDBObject("$setIntersection", intersectionList);
    
    ArrayList<Bson> pipeline = new ArrayList<>();
    pipeline.add(match(Filters.in("requiredCertificates", certs)));
    pipeline.add(addFields(new Field<>("matchCert", intersection)));
    pipeline.add(addFields(new Field<>("matchSize", new BasicDBObject( "$size", "$matchCert" )) ));
    pipeline.add(sort(hasDrivingLicense?Sorts.descending("driverLicenseRequired"):Sorts.ascending("driverLicenseRequired")));
    pipeline.add(sort(Sorts.descending("matchSize")));
    pipeline.add(limit(limitResult));
    jobs.aggregate(pipeline).into(docs);
    return docs;
  }

  @PostConstruct
  public void init() {
    jobs = jobsDb.getCollection("job");
  }
}
