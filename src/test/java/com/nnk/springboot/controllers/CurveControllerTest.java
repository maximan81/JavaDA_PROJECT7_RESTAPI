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

@Tag("CurveControllerTest")
@DisplayName("Curve controller test logic")
public class CurveControllerTest extends AbstractTest {

  @Test
  @DisplayName("home, should display bid list page")
  public void home_ShouldReturnHomePage() throws Exception {
    this.mvc.perform(get("/user/curvePoint/list"))
            .andExpect(view().name("curvePoint/list"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("1")))
            .andExpect(content().string(containsString("5")))
            .andExpect(content().string(containsString("10.0")))
            .andExpect(content().string(containsString("20.0")))
            .andDo(print());
  }

  @Test
  @DisplayName("AddCurvePoint should display add curve point form")
  public void addCurvePointForm_ShouldDisplayAddCurvePointForm() throws Exception {
    mvc.perform(get("/user/curvePoint/add"))
            .andExpect(view().name("curvePoint/add"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("validate should return error for given blank form fields")
  public void validate_ShouldReturnError_forGivenBlankFormFields() throws Exception {
    mvc.perform(post("/user/curvePoint/validate")
                    .param("curveId", "")
                    .param("term", "")
                    .param("value", ""))
            .andExpect(model().attributeHasFieldErrors(
                    "curvePoint", "curveId", "term", "value"))
            .andExpect(view().name("curvePoint/add"))
            .andExpect(status().isOk())
            .andDo(print());
  }


  @Test
  @DisplayName("validate should added new curvePoint for given correct form fields")
  public void validate_ShouldAddNewCurvePoint_forGivenCorrectFormFields() throws Exception {
    mvc.perform(post("/user/curvePoint/validate")
                    .param("curveId", "24")
                    .param("term", "2.01")
                    .param("value", "5.23"))
            .andExpect(view().name("redirect:/user/curvePoint/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("showUpdateForm should redirect to curve list page for given non existing curve id")
  public void showUpdateForm_ShouldRedirectToCurvePointListForm_ForGivenNotExistsCurveId() throws Exception {
    mvc.perform(get("/user/curvePoint/update/1000"))
            .andExpect(view().name("redirect:/user/curvePoint/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("showUpdateForm should display update form for given existing curve id")
  public void showUpdateForm_ShouldDisplayUpdateForm_ForGivenExistCurveId() throws Exception {
    mvc.perform(get("/user/curvePoint/update/2"))
            .andExpect(view().name("curvePoint/update"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("updateCurvePoint should return error for given blank form fields")
  public void UpdateCurvePoint_ShouldReturnError_forGivenBlankFormFields() throws Exception {
    mvc.perform(post("/user/curvePoint/update/2")
                    .param("curveId", "")
                    .param("term", "")
                    .param("value", ""))
            .andExpect(model().attributeHasFieldErrors(
                    "curvePoint", "curveId", "term", "value"))
            .andExpect(view().name("curvePoint/update"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("updateCurvePoint should update curve for given correct form fields")
  public void updateCurvePoint_ShouldUpdateCurve_forGivenCorrectFormFields() throws Exception {
    mvc.perform(post("/user/curvePoint/update/2")
                    .param("curveId", "2")
                    .param("term", "3.80")
                    .param("value", "7.09"))
            .andExpect(view().name("redirect:/user/curvePoint/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }


  @Test
  @DisplayName("deleteCurvePoint should redirect to bid list page for given not existing bid id")
  public void deleteCurvePoint_ShouldRedirectToCurvePointList_ForGivenNotExistCurveId() throws Exception {
    mvc.perform(get("/user/curvePoint/delete/2000"))
            .andExpect(view().name("redirect:/user/curvePoint/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("deleteCurvePoint should delete curve for given existing curve id")
  public void deleteCurvePoint_ShouldDeleteCurvePoint_ForGivenExistCurveId() throws Exception {
    mvc.perform(get("/user/curvePoint/delete/5"))
            .andExpect(view().name("redirect:/user/curvePoint/list"))
            .andExpect(status().is3xxRedirection())
            .andExpect(content().string(not(containsString("15"))))
            .andDo(print());
  }
}