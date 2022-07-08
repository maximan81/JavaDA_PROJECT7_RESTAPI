package com.nnk.springboot.controllers;

import junit.framework.TestCase;
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

@Tag("RatingControllerTest")
@DisplayName("Rating controller test logic")
public class RatingControllerTest extends AbstractTest {

  @Test
  @DisplayName("home, should display rating list page")
  public void home_ShouldReturnHomePage() throws Exception {
    this.mvc.perform(get("/user/rating/list"))
            .andExpect(view().name("rating/list"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Moodys Rating 1")))
            .andExpect(content().string(containsString("Sand PRating 1")))
            .andExpect(content().string(containsString("Fitch Rating 1")))
            .andExpect(content().string(containsString("10")))
            .andDo(print());
  }

  @Test
  @DisplayName("AddRating should display add rating  form")
  public void addRatingForm_ShouldDisplayAddRatingForm() throws Exception {
    mvc.perform(get("/user/rating/add"))
            .andExpect(view().name("rating/add"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("validate should return error for given blank form fields")
  public void validate_ShouldReturnError_forGivenBlankFormFields() throws Exception {
    mvc.perform(post("/user/rating/validate")
                    .param("moodysRating", "")
                    .param("sandPRating", "")
                    .param("fitchRating", ""))
            .andExpect(model().attributeHasFieldErrors(
                    "rating", "moodysRating", "sandPRating", "fitchRating"))
            .andExpect(view().name("rating/add"))
            .andExpect(status().isOk())
            .andDo(print());
  }


  @Test
  @DisplayName("validate should added new rating for given correct form fields")
  public void validate_ShouldAddNewRating_forGivenCorrectFormFields() throws Exception {
    mvc.perform(post("/user/rating/validate")
                    .param("moodysRating", "moodys rating 6")
                    .param("sandPRating", "sand PRating 6")
                    .param("fitchRating", "fitch rating 6")
                    .param("orderNumber", " 60"))
            .andExpect(view().name("redirect:/user/rating/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("showUpdateForm should redirect to rating list page for given non existing rating id")
  public void showUpdateForm_ShouldRedirectToRatingListForm_ForGivenNotExistsRatingId() throws Exception {
    mvc.perform(get("/user/rating/update/1000"))
            .andExpect(view().name("redirect:/user/rating/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("showUpdateForm should display update form for given existing rating id")
  public void showUpdateForm_ShouldDisplayUpdateForm_ForGivenExistRatingId() throws Exception {
    mvc.perform(get("/user/rating/update/2"))
            .andExpect(view().name("rating/update"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("updateRating should return error for given blank form fields")
  public void UpdateRating_ShouldReturnError_forGivenBlankFormFields() throws Exception {
    mvc.perform(post("/user/rating/update/3")
                    .param("moodysRating", "")
                    .param("sandPRating", "")
                    .param("fitchRating", "")
                    .param("orderNumber", "23"))
            .andExpect(model().attributeHasFieldErrors(
                    "rating", "moodysRating", "sandPRating", "fitchRating"))
            .andExpect(view().name("rating/update"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("updateRating should update rating for given correct form fields")
  public void updateRating_ShouldUpdateRating_forGivenCorrectFormFields() throws Exception {
    mvc.perform(post("/user/rating/update/3")
                    .param("moodysRating", "moodys rating 6")
                    .param("sandPRating", "sand PRating 6")
                    .param("fitchRating", "fitch rating 6")
                    .param("orderNumber", "23"))
            .andExpect(view().name("redirect:/user/rating/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }


  @Test
  @DisplayName("deleteRating should redirect to rating  list page for given not existing rating  id")
  public void deleteRating_ShouldRedirectToRatingList_ForGivenNotExistRatingId() throws Exception {
    mvc.perform(get("/user/rating/delete/500"))
            .andExpect(view().name("redirect:/user/rating/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("deleteRating should delete rating for given existing rating id")
  public void deleteRating_ShouldDeleteRating_ForGivenExistRatingId() throws Exception {
    mvc.perform(get("/user/rating/delete/4"))
            .andExpect(view().name("redirect:/user/rating/list"))
            .andExpect(status().is3xxRedirection())
            .andExpect(content().string(not(containsString("moodys rating 4"))))
            .andDo(print());
  }
}