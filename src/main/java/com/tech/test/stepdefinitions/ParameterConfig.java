package com.tech.test.stepdefinitions;

import io.cucumber.java.ParameterType;

public class ParameterConfig {

  @ParameterType("mark|unmark")
  public String quoteMark(String input) {
    return input;
  }
}
