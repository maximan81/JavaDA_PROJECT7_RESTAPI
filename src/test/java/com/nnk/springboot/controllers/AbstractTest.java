package com.nnk.springboot.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractTest {
  @Autowired
  protected MockMvc mvc;

  @Autowired
  WebApplicationContext webApplicationContext;

  @BeforeEach
  protected void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .build();
  }
}
