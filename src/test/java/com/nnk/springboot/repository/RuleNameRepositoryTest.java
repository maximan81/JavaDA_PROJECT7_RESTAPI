package com.nnk.springboot.repository;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

@Tag("RuleNameRepositoryTest")
@DisplayName("rule repository implementation class")
@SpringBootTest
public class RuleNameRepositoryTest {

  @Autowired
  private RuleNameRepository ruleNameRepository;

  private RuleName rule;

  @BeforeEach
  public void setUp() {
    rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
    ruleNameRepository.save(rule);
  }

  @Test
  @DisplayName("saveRuleName, should save a ruleName")
  public void saveRuleName_ShouldSaveRuleName() {
    // GIVEN
    RuleName ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

    // WHEN
    RuleName result = ruleNameRepository.save(ruleName);

    // THEN
    Assert.assertNotNull(result.getId());
    Assert.assertEquals(result.getName(), "Rule Name", "Rule Name");
  }

  @Test
  @DisplayName("getAllRuleName, should return ruleName list")
  public void getAllRuleName_ShouldReturnRuleNameList() {
    // GIVEN
    // WHEN
    List<RuleName> listResult = ruleNameRepository.findAll();

    // THEN
    Assert.assertTrue(listResult.size() > 0);
  }

  @Test
  @DisplayName("getOneRuleName, should return a ruleName")
  public void getOneRuleName_ShouldReturnRuleName() {
    // GIVEN
    Integer id = rule.getId();
    // WHEN
    Optional<RuleName> ruleName = ruleNameRepository.findById(id);

    // THEN
    Assert.assertTrue(ruleName.isPresent());

  }

  @Test
  @DisplayName("deleteRuleName, should delete a ruleName")
  public void deleteRuleName_ShouldDeleteRuleName() {
    // GIVEN
    Integer id = rule.getId();

    // WHEN
    ruleNameRepository.delete(rule);
    Optional<RuleName> ruleName = ruleNameRepository.findById(id);

    // THEN
    Assert.assertFalse(ruleName.isPresent());
  }

  @Test
  @DisplayName("updateRuleName, should update a ruleName")
  public void updateRuleName_ShouldUpdateRuleName() {
    // GIVEN
    rule.setName("Rule Name update");

    // WHEN
    rule = ruleNameRepository.save(rule);

    // THEN
    Assert.assertEquals(rule.getName(), "Rule Name update", "Rule Name update");
  }

}