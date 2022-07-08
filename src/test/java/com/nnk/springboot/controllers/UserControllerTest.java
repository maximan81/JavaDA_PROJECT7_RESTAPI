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

@Tag("UserControllerTest")
@DisplayName("User controller test logic")
public class UserControllerTest extends AbstractTest {

  @Test
  @DisplayName("home, should display user list page")
  public void home_ShouldReturnHomePage() throws Exception {
    this.mvc.perform(get("/admin/user/list"))
            .andExpect(view().name("user/list"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("User 1")))
            .andExpect(content().string(containsString("user 1")))
            .andExpect(content().string(containsString("USER")))
            .andDo(print());
  }

  @Test
  @DisplayName("AddUser should display add user form")
  public void addUserForm_ShouldDisplayAddUserForm() throws Exception {
    mvc.perform(get("/admin/user/add"))
            .andExpect(view().name("user/add"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("validate should return error for given blank form fields")
  public void validate_ShouldReturnError_forGivenBlankFormFields() throws Exception {
    mvc.perform(post("/admin/user/validate")
                    .param("username", "")
                    .param("password", "")
                    .param("fullname", "")
                    .param("role", ""))
            .andExpect(model().attributeHasFieldErrors(
                    "user", "username", "password", "fullname", "role"))
            .andExpect(view().name("user/add"))
            .andExpect(status().isOk())
            .andDo(print());
  }


  @Test
  @DisplayName("validate should added new user for given correct form fields")
  public void validate_ShouldAddNewUser_forGivenCorrectFormFields() throws Exception {
    mvc.perform(post("/admin/user/validate")
                    .param("username", "User new")
                    .param("password", "123")
                    .param("fullname", "user new")
                    .param("role", "USER"))
            .andExpect(view().name("redirect:/admin/user/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("showUpdateForm should redirect to user list page for given non existing user id")
  public void showUpdateForm_ShouldRedirectToUserListForm_ForGivenNotExistsUserId() throws Exception {
    mvc.perform(get("/admin/user/update/80"))
            .andExpect(view().name("redirect:/admin/user/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("showUpdateForm should display update form for given existing user id")
  public void showUpdateForm_ShouldDisplayUpdateForm_ForGivenExistUserId() throws Exception {
    mvc.perform(get("/admin/user/update/2"))
            .andExpect(view().name("user/update"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("updateUser should return error for given blank form fields")
  public void UpdateUser_ShouldReturnError_forGivenBlankFormFields() throws Exception {
    mvc.perform(post("/admin/user/update/3")
                    .param("username", "")
                    .param("password", "")
                    .param("fullname", "")
                    .param("role", ""))
            .andExpect(model().attributeHasFieldErrors(
                    "user", "username", "password", "fullname", "role"))
            .andExpect(view().name("user/update"))
            .andExpect(status().isOk())
            .andDo(print());
  }

  @Test
  @DisplayName("updateUser should update user for given correct form fields")
  public void updateUser_ShouldUpdateUser_forGivenCorrectFormFields() throws Exception {
    mvc.perform(post("/admin/user/update/2")
                    .param("username", "User update")
                    .param("password", "123")
                    .param("fullname", "user update")
                    .param("role", "USER"))
            .andExpect(view().name("redirect:/admin/user/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }


  @Test
  @DisplayName("deleteUser should redirect to user  list page for given not existing user  id")
  public void deleteUser_ShouldRedirectToUserList_ForGivenNotExistUserId() throws Exception {
    mvc.perform(get("/admin/user/delete/500"))
            .andExpect(view().name("redirect:/admin/user/list"))
            .andExpect(status().is3xxRedirection())
            .andDo(print());
  }

  @Test
  @DisplayName("deleteUser should delete user for given existing user id")
  public void deleteUser_ShouldDeleteUser_ForGivenExistUserId() throws Exception {
    mvc.perform(get("/admin/user/delete/3"))
            .andExpect(view().name("redirect:/admin/user/list"))
            .andExpect(status().is3xxRedirection())
            .andExpect(content().string(not(containsString("User 3"))))
            .andDo(print());
  }
}