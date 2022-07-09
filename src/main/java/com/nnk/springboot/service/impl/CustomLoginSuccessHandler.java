package com.nnk.springboot.service.impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * CustomLoginSuccessHandler. class that implement
 * Success login business logic
 */
@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    String targetUrl = determineTargetUrl(authentication);

    if (response.isCommitted()) {
      return;
    }

    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    redirectStrategy.sendRedirect(request, response, targetUrl);
  }

  /**
   * determineTargetUrl. Method that determine
   * user or admin url redirection.
   *
   * @param authentication an authentication
   * @return String url
   */
  protected String determineTargetUrl(Authentication authentication) {
    String url = "/login?error=true";

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    List<String> roles = new ArrayList<>();

    for (GrantedAuthority authority: authorities) {
      roles.add(authority.getAuthority());
    }

    if (roles.contains("ROLE_ADMIN")) {
      url = "/admin/user/list";
    } else if (roles.contains("ROLE_USER")) {
      url = "/user/bidList/list";
    }

    return url;
  }
}
