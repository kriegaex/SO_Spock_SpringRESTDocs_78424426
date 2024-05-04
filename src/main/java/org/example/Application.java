package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    new SpringApplication(Application.class).run(args);
  }

  @RestController
  private static class SampleController {
    @RequestMapping("/")
    public String index() {
      return "Hello, World";
    }
  }
}
