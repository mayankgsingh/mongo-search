package com.ms.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Configuration and entry point to launch application.
 * 
 * @author Mayank
 *
 */
@SpringBootApplication(scanBasePackages = "com.ms")
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
