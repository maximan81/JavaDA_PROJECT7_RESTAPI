package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CurvePointRepository. Interface that allows to access and
 * persist data between CurvePoint object and poseidenDb database
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
