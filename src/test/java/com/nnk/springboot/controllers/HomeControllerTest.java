package com.nnk.springboot.controllers;

import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Tag("HomeControllerTest")
@DisplayName("Home controller test logic")
public class HomeControllerTest extends AbstractTest {

  @Test
  @DisplayName("home, should display home page")
  public void home_ShouldReturnHomePage() throws Exception {
    this.mvc.perform(get("/"))
            .andExpect(view().name("home"))
            .andExpect(status().isOk())
            .andDo(print());
  }



  @Test
  @DisplayName("adminHome, should display admin home page")
  @WithUserDetails("admin")
  public void adminHome_ShouldReturnAdminHomePage() throws Exception {
    this.mvc.perform(get("/admin/home"))
            .andExpect(view().name("redirect:/admin/user/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }
}