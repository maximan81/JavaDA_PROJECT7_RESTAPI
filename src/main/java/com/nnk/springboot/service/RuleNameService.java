package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;

import java.util.Optional;

/**
 * RuleNameService interface structure the business logic
 * of RuleName.
 */
public interface RuleNameService {

  /**
   * getRuleNames. Method that get all RuleName from database.
   *
   * @return Iterable RuleName
   */
  Iterable<RuleName> getRuleNames();

  /**
   * saveRuleName. Method that save given ruleName in database.
   *
   * @param ruleName an RuleName
   * @return an RuleName
   */
  RuleName saveRuleName(RuleName ruleName);

  /**
   * getRuleName. Method that get one RuleName from database.
   *
   * @param id an RuleName id
   * @return optional RuleName
   */
  Optional<RuleName> getRuleName(Integer id);

  /**
   * deleteRuleName. Method that delete given RuleName in database.
   *
   * @param ruleName a RuleName
   */
  void deleteRuleName(RuleName ruleName);
}
