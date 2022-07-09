package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;

import java.util.Optional;

/**
 * UserService interface structure the business logic
 * of User.
 */
public interface UserService {

  /**
   * getUsers. Method that get all User from database.
   *
   * @return Iterable User
   */
  Iterable<User> getUsers();

  /**
   * saveUser. Method that save given User in database.
   *
   * @param user an User
   * @return an User
   */
  User saveUser(User user);

  /**
   * getUser. Method that get one User from database.
   *
   * @param id an User id
   * @return optional User
   */
  Optional<User> getUser(Integer id);

  /**
   * deleteUser. Method that delete given User in database.
   *
   * @param user a User
   */
  void deleteUser(User user);
}
