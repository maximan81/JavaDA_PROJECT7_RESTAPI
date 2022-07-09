package com.nnk.springboot.controllers;

import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Tag("RuleNameControllerTest")
@DisplayName("Rule controller test logic")
public class RuleNameControllerTest extends AbstractTest {

  @Test
  @DisplayName("home, should display ruleName list page")
  @WithUserDetails("user 1")
  public void home_ShouldReturnHomePage() throws Exception {
    this.mvc.perform(get("/user/ruleName/list"))
            .andExpect(view().name("ruleName/list"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("rule name 1")))
            .andExpect(content().string(containsString("description 1")))
            .andExpect(content().string(containsString("template 1")))
            .andExpect(content().string(containsString("json 1")))
            .andExpect(content().string(containsString("sqlStr 1")))
            .andExpect(content().string(containsString("sqlPart 1")))
            .andDo(print());
  }

  @Test
  @DisplayName("AddRuleNameForm should display add ruleName  form")
  @WithUserDetails("user 1")
  public void addRuleNameForm_ShouldDisplayAddRuleNameForm() throws Exception {
    mvc.perform(get("/user/ruleName/add"))
            .andExpect(view().name("ruleName/add"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("validate should return error for given blank form fields")
  @WithUserDetails("user 1")
  public void validate_ShouldReturnError_forGivenBlankFormFields() throws Exception {
    mvc.perform(post("/user/ruleName/validate")
                    .param("name", "")
                    .param("description", "")
                    .param("template", "")
                    .param("json", "")
                    .param("sqlStr", "")
                    .param("sqlPart", ""))
            .andExpect(model().attributeHasFieldErrors(
                    "ruleName", "name", "description", "template", "json", "sqlStr", "sqlPart"))
            .andExpect(view().name("ruleName/add"))
            .andExpect(status().isOk())
            .andDo(print());
  }


  @Test
  @DisplayName("validate should added new ruleName for given correct form fields")
  @WithUserDetails("user 1")
  public void validate_ShouldAddNewRuleName_forGivenCorrectFormFields() throws Exception {
    mvc.perform(post("/user/ruleName/validate")
                    .param("name", "name 6")
                    .param("description", "description 6")
                    .param("template", "template 6")
                    .param("json", "json 6")
                    .param("sqlStr", "sqlStr 6")
                    .param("sqlPart", "sqlPart 6"))
            .andExpect(view().name("redirect:/user/ruleName/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("showUpdateForm should redirect to ruleName list page for given non existing ruleName id")
  @WithUserDetails("user 1")
  public void showUpdateForm_ShouldRedirectToRuleNameListForm_ForGivenNotExistsRuleNameId() throws Exception {
    mvc.perform(get("/user/ruleName/update/100"))
            .andExpect(view().name("redirect:/user/ruleName/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("showUpdateForm should display update form for given existing ruleName id")
  @WithUserDetails("user 1")
  public void showUpdateForm_ShouldDisplayUpdateForm_ForGivenExistRuleNameId() throws Exception {
    mvc.perform(get("/user/ruleName/update/1"))
            .andExpect(view().name("ruleName/update"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("updateRuleName should return error for given blank form fields")
  @WithUserDetails("user 1")
  public void UpdateRuleName_ShouldReturnError_forGivenBlankFormFields() throws Exception {
    mvc.perform(post("/user/ruleName/update/1")
                    .param("name", "")
                    .param("description", "")
                    .param("template", "")
                    .param("json", "")
                    .param("sqlStr", "")
                    .param("sqlPart", ""))
            .andExpect(model().attributeHasFieldErrors(
                    "ruleName", "name", "description", "template", "json", "sqlStr", "sqlPart"))
            .andExpect(view().name("ruleName/update"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("updateRuleName should update ruleName for given correct form fields")
  @WithUserDetails("user 1")
  public void updateRuleName_ShouldUpdateRuleName_forGivenCorrectFormFields() throws Exception {
    mvc.perform(post("/user/ruleName/update/1")
                    .param("name", "name 1")
                    .param("description", "description 1")
                    .param("template", "template 1")
                    .param("json", "json update")
                    .param("sqlStr", "sqlStr update")
                    .param("sqlPart", "sqlPart 1"))
            .andExpect(view().name("redirect:/user/ruleName/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }


  @Test
  @DisplayName("deleteRuleName should redirect to ruleName  list page for given not existing ruleName  id")
  @WithUserDetails("user 1")
  public void deleteRuleName_ShouldRedirectToRuleNameList_ForGivenNotExistRuleNameId() throws Exception {
    mvc.perform(get("/user/ruleName/delete/50"))
            .andExpect(view().name("redirect:/user/ruleName/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("deleteRuleName should delete ruleName for given existing ruleName id")
  @WithUserDetails("user 1")
  public void deleteRuleName_ShouldDeleteRuleName_ForGivenExistRuleNameId() throws Exception {
    mvc.perform(get("/user/ruleName/delete/4"))
            .andExpect(view().name("redirect:/user/ruleName/list"))
            .andExpect(status().is3xxRedirection())
            .andExpect(content().string(not(containsString("name 4"))))
            .andDo(print());
  }
}