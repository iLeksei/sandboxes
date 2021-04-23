package com.example.loggingdemo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/web-app")
public class MainController {

//  @Value("${application.version:0.0}")
  private String applicationVersion = "0.1";

  @GetMapping("version")
  public String applicationVersion() {
    log.info("getting application version: {}", applicationVersion);
    System.out.println(System.getProperty("user.home"));
    return applicationVersion;
  }


}
