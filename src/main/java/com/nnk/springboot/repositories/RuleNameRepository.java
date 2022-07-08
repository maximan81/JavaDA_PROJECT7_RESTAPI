package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * RuleNameRepository. Interface that allows to access and
 * persist data between RuleName object and poseidenDb database
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
}
