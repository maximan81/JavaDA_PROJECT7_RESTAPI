package com.nnk.springboot.repository;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@Tag("UserRepositoryTest")
@DisplayName("user repository implementation class")
@SpringBootTest
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  private User user;

  private User user2;

  private BCryptPasswordEncoder encoder;

  @BeforeEach
  public void setUp() {
    encoder = new BCryptPasswordEncoder();
    String passwordEncoded = encoder.encode("password test");
    user = new User(1, "username test", passwordEncoded, "fullname test", "USER");
    user2 = new User(2, "username test", passwordEncoded, "fullname test", "USER");
    userRepository.save(user);
    userRepository.save(user2);
  }

  @Test
  @DisplayName("saveUser, should save a user")
  public void saveUser_ShouldSaveUser() {
    // GIVEN
    String passwordEncoder2 = encoder.encode("password test 2");
    User  saveUser = new User(2, "username test 2", passwordEncoder2, "fullname test 2", "USER");

    // WHEN
    User result = userRepository.save(saveUser);

    // THEN
    Assert.assertNotNull(result.getId());
    Assert.assertEquals(result.getUsername(), "username test 2", "username test 2");
  }

  @Test
  @DisplayName("getAllUser, should return user list")
  public void getAllUser_ShouldReturnUserList() {
    // GIVEN
    // WHEN
    List<User> listResult = userRepository.findAll();

    // THEN
    Assert.assertTrue(listResult.size() > 0);
  }

  @Test
  @DisplayName("getOneUser, should return a user")
  public void getOneUser_ShouldReturnUser() {
    // GIVEN
    Integer id = user2.getId();
    // WHEN
    Optional<User> user = userRepository.findById(id);

    // THEN
    Assert.assertTrue(user.isPresent());

  }

  @Test
  @DisplayName("deleteUser, should delete a user")
  public void deleteUser_ShouldDeleteUser() {
    // GIVEN
    Integer id = user.getId();

    // WHEN
    userRepository.delete(user);
    Optional<User> user = userRepository.findById(id);

    // THEN
    Assert.assertFalse(user.isPresent());
  }

  @Test
  @DisplayName("updateUser, should update a user")
  public void updateUser_ShouldUpdateUser() {
    // GIVEN
    user.setUsername("username test 2 update");

    // WHEN
    user = userRepository.save(user);

    // THEN
    Assert.assertEquals(user.getUsername(), "username test 2 update", "username test 2 update");
  }

  @Test
  @DisplayName("passwordEncoded, should return user encoded password")
  public void passwordEncoded_ShouldReturnEncodedPassword() {
    // GIVEN
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String pw = encoder.encode("123456");
    User passwordEncodeUser = new User(2, "username encoded password", pw, "fullname encoded password", "USER");
    userRepository.save(passwordEncodeUser);

    // WHEN
    Optional<User> resultPasswordUser = userRepository.findById(2);

    // THEN
    assertThat(resultPasswordUser.get().getPassword()).isEqualTo(pw);
  }
  
}