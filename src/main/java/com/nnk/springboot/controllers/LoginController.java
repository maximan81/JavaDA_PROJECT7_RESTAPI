package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.impl.UserSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  private static final Logger log = LoggerFactory.getLogger(LoginController.class);
  @Autowired
  private UserSecurityService userSecurityService;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/login")
  public String login(Model model) {
    return "login";
  }


  @GetMapping("secure/article-details")
  public ModelAndView getAllUserArticles() {
    ModelAndView mav = new ModelAndView();
    mav.addObject("users", userRepository.findAll());
    mav.setViewName("user/list");
    return mav;
  }

  @GetMapping("/app/error")
  public String error(Model model) {

    String errorMessage = "You are not authorized for the requested data.";
    model.addAttribute("errorMsg", errorMessage);

    return "403";
  }
}
