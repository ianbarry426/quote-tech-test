package com.tech.test.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber.json"},
        glue = {"com.tech.test.stepdefinitions"},
        features = {"classpath:features"},
        tags = "@FavQuote or @ListQuotes")
public class CucumberRunner {

  public static void main(String[] args) {
    JUnitCore.main(CucumberRunner.class.getCanonicalName());
  }
}