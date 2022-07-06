package com.nnk.springboot.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("BidListControllerTest")
@DisplayName("Account controller test logic")
public class BidListControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  WebApplicationContext webApplicationContext;

  @BeforeEach
  protected void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .build();
  }


  @Test
  @DisplayName("home, should display bid list page")
  public void home_ShouldReturnHomePage() throws Exception {
    mvc.perform(get("/bidList/list"))
            .andExpect(view().name("bidList/list"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("1")))
            .andExpect(content().string(containsString("account 1")))
            .andExpect(content().string(containsString("type 1")))
            .andExpect(content().string(containsString("1.0")))
            .andDo(print());
  }

  @Test
  @DisplayName("addBidForm should display add bid form")
  public void addBidForm_ShouldDisplayAddBidForm() throws Exception {
    mvc.perform(get("/bidList/add"))
            .andExpect(view().name("bidList/add"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("validate should return error for given blank form fields")
  public void validate_ShouldReturnError_forGivenBlankFormFields() throws Exception {
    mvc.perform(post("/bidList/validate")
                    .param("account", "")
                    .param("type", "")
                    .param("bidQuantity", "5"))
            .andExpect(model().attributeHasFieldErrors(
                    "bidListDto", "account", "type"))
            .andExpect(view().name("bidList/add"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("validate should added new bid for given correct form fields")
  public void validate_ShouldAddNewBid_forGivenCorrectFormFields() throws Exception {
    mvc.perform(post("/bidList/validate")
                    .param("account", "account test")
                    .param("type", "type test")
                    .param("bidQuantity", "5d"))
            .andExpect(view().name("redirect:/bidList/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("showUpdateForm should redirect to bid list page for given non existing bid id")
  public void showUpdateForm_ShouldRedirectToBidListForm_ForGivenNotExistsBidId() throws Exception {
    mvc.perform(get("/bidList/update/1000"))
            .andExpect(view().name("redirect:/bidList/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("showUpdateForm should display update form for given existing bid id")
  public void showUpdateForm_ShouldDisplayUpdateForm_ForGivenExistBidId() throws Exception {
    mvc.perform(get("/bidList/update/2"))
            .andExpect(view().name("bidList/update"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("updateBid should return error for given blank form fields")
  public void UpdateBid_ShouldReturnError_forGivenBlankFormFields() throws Exception {
    mvc.perform(post("/bidList/update/2")
                    .param("account", "")
                    .param("type", "")
                    .param("bidQuantity", "5"))
            .andExpect(model().attributeHasFieldErrors(
                    "bidListDto", "account", "type"))
            .andExpect(view().name("bidList/update"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("updateBid should update bid for given correct form fields")
  public void updateBid_ShouldUpdateBid_forGivenCorrectFormFields() throws Exception {
    mvc.perform(post("/bidList/update/2")
                    .param("account", "Account update")
                    .param("type", "type update")
                    .param("bidQuantity", "7"))
            .andExpect(view().name("redirect:/bidList/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("deleteBid should redirect to bid list page for given not existing bid id")
  public void deleteBid_ShouldRedirectToBidList_ForGivenNotExistBidId() throws Exception {
    mvc.perform(get("/bidList/delete/2000"))
            .andExpect(view().name("redirect:/bidList/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("deleteBid should delete bid for given existing bid id")
  public void deleteBid_ShouldDeleteBid_ForGivenExistBidId() throws Exception {
    mvc.perform(get("/bidList/delete/5"))
            .andExpect(view().name("bidList/list"))
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("account 5"))))
            .andDo(print());
  }
}