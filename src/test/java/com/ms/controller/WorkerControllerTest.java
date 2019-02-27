package com.ms.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.ms.job.App;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes=App.class)
public class WorkerControllerTest {
  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  WebApplicationContext webApplicationContext;

  @Test
  public void testSuggestions() throws Exception {
    this.mockMvc.perform(get("/worker/12/suggest"))
    .andExpect(status().isOk())
    .andDo(print());
  }
}
