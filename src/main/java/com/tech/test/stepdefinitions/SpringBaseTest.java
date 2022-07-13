package com.tech.test.stepdefinitions;

import com.tech.test.config.TestConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class SpringBaseTest {

    @Before
    public void setUpCucumberContext() {

    }
}
