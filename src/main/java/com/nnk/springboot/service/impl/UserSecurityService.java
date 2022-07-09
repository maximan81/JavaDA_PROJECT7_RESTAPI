package com.nnk.springboot.service.impl;

import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserSecurityService. class that implement
 * user security business logic
 */
@Service
public class UserSecurityService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;


  /**
   * loadUserByUsername. Method that get user details from database.
   *
   * @param username a username
   * @return user details
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username);
  }

}
