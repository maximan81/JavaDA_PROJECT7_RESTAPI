package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;
import com.nnk.springboot.utility.ConvertTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@Tag("BidListServiceImplTest")
@DisplayName("user service implementation class")
public class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  private UserService userService;

  private User user;

  @BeforeEach
  public void setUp() {
    userService = new UserServiceImpl(userRepository);

    user = new User(1, "User", "123", "user", "USER");
  }

  @Test
  @DisplayName("getAllUsers, should return a list of Useu")
  public void getAllUsers_ShouldReturnListOfUser() {
    // GIVEN
    when(userRepository.findAll()).thenReturn(List.of(user));

    // WHEN
    Iterable<User> results = userService.getUsers();

    // THEN
    verify(userRepository, times(1)).findAll();
    assertThat(results).contains(user);
  }

  @Test
  @DisplayName("getOneUser, should return a user")
  public void getOneUser_ShouldReturnUser() {
    // GIVEN
    Integer id = 1;
    when(userRepository.findById(id)).thenReturn(Optional.ofNullable(user));

    // WHEN
    Optional<User> result = userService.getUser(id);

    // THEN
    verify(userRepository, times(1)).findById(id);
    assertThat(result.get()).isEqualTo(user);
  }

  @Test
  @DisplayName("deleteUser, should delete a user")
  public void deleteUser_ShouldDeleteUser() {
    // GIVEN
    doNothing().when(userRepository).delete(user);

    // WHEN
    userService.deleteUser(user);

    // THEN
    assertThat(userService.getUsers()).doesNotContain(user);
  }

  @Test
  @DisplayName("saveUser should save a user")
  public void saveUser_ShouldAddNewUser() {
    // GIVEN
    when(userRepository.save(user)).thenReturn(user);

    // WHEN
    User result = userService.saveUser(user);

    // THEN
    assertThat(result).isEqualTo(user);
  }
}