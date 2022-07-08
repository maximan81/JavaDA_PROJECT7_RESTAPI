package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserServiceImpl. class that implement
 * User business logic
 */
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<User> getUsers() {
    return userRepository.findAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<User> getUser(Integer id) {
    return userRepository.findById(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteUser(User user) {
    userRepository.delete(user);
  }
}
