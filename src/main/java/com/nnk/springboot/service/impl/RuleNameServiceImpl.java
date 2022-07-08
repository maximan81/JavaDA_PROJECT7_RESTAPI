package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * RuleNameServiceImpl. class that implement
 * RuleName business logic
 */
@Service
public class RuleNameServiceImpl implements RuleNameService {

  private final RuleNameRepository ruleNameRepository;

  public RuleNameServiceImpl(RuleNameRepository ruleNameRepository) {
    this.ruleNameRepository = ruleNameRepository;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<RuleName> getRuleNames() {
    return ruleNameRepository.findAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public RuleName saveRuleName(RuleName ruleName) {
    return ruleNameRepository.save(ruleName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<RuleName> getRuleName(Integer id) {
    return ruleNameRepository.findById(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteRuleName(RuleName ruleName) {
    ruleNameRepository.delete(ruleName);
  }
}
