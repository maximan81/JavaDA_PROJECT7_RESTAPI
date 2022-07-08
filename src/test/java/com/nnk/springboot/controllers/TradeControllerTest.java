package com.nnk.springboot.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Tag("TradeControllerTest")
@DisplayName("Trade controller test logic")
public class TradeControllerTest extends AbstractTest {

  @Test
  @DisplayName("home, should display trade list page")
  public void home_ShouldReturnHomePage() throws Exception {
    this.mvc.perform(get("/trade/list"))
            .andExpect(view().name("trade/list"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("trade account 1")))
            .andExpect(content().string(containsString("type 1")))
            .andExpect(content().string(containsString("12")))
            .andDo(print());
  }

  @Test
  @DisplayName("AddTrade should display add trade form")
  public void addTradeForm_ShouldDisplayAddTradeForm() throws Exception {
    mvc.perform(get("/trade/add"))
            .andExpect(view().name("trade/add"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("validate should return error for given blank form fields")
  public void validate_ShouldReturnError_forGivenBlankFormFields() throws Exception {
    mvc.perform(post("/trade/validate")
                    .param("account", "")
                    .param("type", "")
                    .param("buyQuantity", ""))
            .andExpect(model().attributeHasFieldErrors(
                    "trade", "account", "type", "buyQuantity"))
            .andExpect(view().name("trade/add"))
            .andExpect(status().isOk())
            .andDo(print());
  }


  @Test
  @DisplayName("validate should added new trade for given correct form fields")
  public void validate_ShouldAddNewTrade_forGivenCorrectFormFields() throws Exception {
    mvc.perform(post("/trade/validate")
                    .param("account", "trade account 6")
                    .param("type", "type 6")
                    .param("buyQuantity", "5"))
            .andExpect(view().name("redirect:/trade/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("showUpdateForm should redirect to trade list page for given non existing trade id")
  public void showUpdateForm_ShouldRedirectToTradeListForm_ForGivenNotExistsTradeId() throws Exception {
    mvc.perform(get("/trade/update/80"))
            .andExpect(view().name("redirect:/trade/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("showUpdateForm should display update form for given existing trade id")
  public void showUpdateForm_ShouldDisplayUpdateForm_ForGivenExistTradeId() throws Exception {
    mvc.perform(get("/trade/update/2"))
            .andExpect(view().name("trade/update"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("updateTrade should return error for given blank form fields")
  public void UpdateTrade_ShouldReturnError_forGivenBlankFormFields() throws Exception {
    mvc.perform(post("/trade/update/3")
                    .param("account", "")
                    .param("type", "")
                    .param("buyQuantity", ""))
            .andExpect(model().attributeHasFieldErrors(
                    "trade", "account", "type", "buyQuantity"))
            .andExpect(view().name("trade/update"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("updateTrade should update trade for given correct form fields")
  public void updateTrade_ShouldUpdateTrade_forGivenCorrectFormFields() throws Exception {
    mvc.perform(post("/trade/update/3")
                    .param("account", "account 3 update")
                    .param("type", "type 6 update")
                    .param("buyQuantity", "23"))
            .andExpect(view().name("redirect:/trade/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }


  @Test
  @DisplayName("deleteTrade should redirect to trade  list page for given not existing trade  id")
  public void deleteTrade_ShouldRedirectToTradeList_ForGivenNotExistTradeId() throws Exception {
    mvc.perform(get("/trade/delete/500"))
            .andExpect(view().name("trade/list"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("deleteTrade should delete trade for given existing trade id")
  public void deleteTrade_ShouldDeleteTrade_ForGivenExistTradeId() throws Exception {
    mvc.perform(get("/trade/delete/4"))
            .andExpect(view().name("trade/list"))
            .andExpect(status().isOk())
            .andExpect(content().string(not(containsString("trade account 4"))))
            .andDo(print());
  }
}