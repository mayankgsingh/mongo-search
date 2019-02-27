package com.ms.job.util;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.mongodb.Block;

@Service
public class PrintBlock implements Block<Document> {

  @Override
  public void apply(Document document) {
    System.out.println(document.toJson());
  }
}
