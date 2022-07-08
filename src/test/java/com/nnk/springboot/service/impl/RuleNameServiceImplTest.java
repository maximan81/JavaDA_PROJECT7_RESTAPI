package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
import com.nnk.springboot.utility.ConvertTo;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@Tag("RuleNameServiceImplTest")
@DisplayName("ruleName service implementation class")
public class RuleNameServiceImplTest {

  @Mock
  private RuleNameRepository ruleNameRepository;

  private RuleNameService ruleNameService;

  private RuleName ruleName;

  @BeforeEach
  public void setUp() {
    ruleNameService = new RuleNameServiceImpl(ruleNameRepository);

    ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
  }

  @Test
  @DisplayName("getAllRuleName, should return a list of ruleName")
  public void getAllRuleName_ShouldReturnListOfRuleName() {
    // GIVEN
    when(ruleNameRepository.findAll()).thenReturn(List.of(ruleName));

    // WHEN
    Iterable<RuleName> results = ruleNameService.getRuleNames();

    // THEN
    verify(ruleNameRepository, times(1)).findAll();
    assertThat(results).contains(ruleName);
  }

  @Test
  @DisplayName("getOneRuleName, should return a ruleName")
  public void getOneRuleName_ShouldReturnRuleName() {
    // GIVEN
    Integer id = 1;
    when(ruleNameRepository.findById(id)).thenReturn(Optional.ofNullable(ruleName));

    // WHEN
    Optional<RuleName> result = ruleNameService.getRuleName(id);

    // THEN
    verify(ruleNameRepository, times(1)).findById(id);
    assertThat(result.get()).isEqualTo(ruleName);
  }

  @Test
  @DisplayName("deleteRuleName, should delete a ruleName")
  public void deleteRuleName_ShouldDeleteRuleName() {
    // GIVEN
    doNothing().when(ruleNameRepository).delete(ruleName);

    // WHEN
    ruleNameService.deleteRuleName(ruleName);

    // THEN
    assertThat(ruleNameService.getRuleNames()).doesNotContain(ruleName);
  }

  @Test
  @DisplayName("saveRuleName, should save a ruleName")
  public void saveBidList() {
    // GIVEN
    when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);

    // WHEN
    RuleName result = ruleNameService.saveRuleName(ruleName);

    // THEN
    assertThat(result).isEqualTo(ruleName);
  }
}