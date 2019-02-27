package com.ms.job.controllers;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.job.dao.JobDao;
import com.ms.job.dao.WorkerDao;

/**
 * REST controller class for Worker entity.
 * 
 * @author Mayank
 *
 */
@RestController
@RequestMapping(path = "/worker")
@Scope(scopeName=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WorkerController {

  @Autowired
  private WorkerDao workerDao;
  @Autowired
  private JobDao jobDao;
  
  /**
   * Job suggestion REST interface.
   * 
   * @param id
   * @return ResponseEntity<List<Document>>
   */
  @RequestMapping(path = "/{id}/suggest", produces = "application/json", method = RequestMethod.GET)
  public ResponseEntity<List<Document>> suggestions(@PathVariable Integer id) {
    Document worker = workerDao.getWorkderById(id);
    ResponseEntity<List<Document>> result = new ResponseEntity<List<Document>>(jobDao.suggestJobs(worker, 3), HttpStatus.OK);
    return result;
  }
}
