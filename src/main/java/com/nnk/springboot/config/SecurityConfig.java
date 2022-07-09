package com.nnk.springboot.config;

import com.nnk.springboot.service.impl.CustomAccessDeniedHandlerService;
import com.nnk.springboot.service.impl.CustomLoginSuccessHandler;
import com.nnk.springboot.service.impl.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserSecurityService userSecurityService;

  /**
   * BCryptPasswordEncoder. An instance of BCryptPasswordEncoder
   * to encrypt the password.
   *
   * @return the password encoder
   */
  @Autowired
  private BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Configure the AuthenticationManagerBuilder to use the password encoder.
   *
   * @param auth AuthenticationManagerBuilder instance
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
  }


  /**
   * AccessDeniedHandler. instance of accessDeniedHandler that intercepts
   * unauthorized routes.
   *
   * @return the  sendRedirect
   */
  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandlerService();
  }

  private static final String[] PUBLIC_MATCHERS = {
          "/css/**",
          "/",
          "/app/**",
          "/home",
          "/access-denied"
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
            .authorizeRequests()
            .antMatchers(PUBLIC_MATCHERS).permitAll()
            .antMatchers("/user/**").hasAnyRole("USER")
            .antMatchers("/admin/**").hasAnyRole("ADMIN")
            .anyRequest().authenticated();

    http
            .csrf().disable().cors().disable()
            .logout()
            .logoutUrl("/app-logout")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessUrl("/login").permitAll()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .successHandler(new CustomLoginSuccessHandler())
            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler())
            .and()
            .rememberMe();
  }
}