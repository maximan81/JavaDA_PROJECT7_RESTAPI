package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

import java.util.Optional;

/**
 * CurvePointService interface structure the business logic
 * of CurvePoint.
 */
public interface CurvePointService {

  /**
   * getCurvePoints. Method that get all CurvePoint from database.
   *
   * @return Iterable CurvePoint
   */
  Iterable<CurvePoint> getCurvePoints();

  /**
   * saveCurvePoint. Method that save given curvePoint in database.
   *
   * @param curvePoint an curvePoint
   * @return an CurvePoint
   */
  CurvePoint saveCurvePoint(CurvePoint curvePoint);

  /**
   * getCurvePoint. Method that get one CurvePoint from database.
   *
   * @param id an CurvePoint id
   * @return optional CurvePoint
   */
  Optional<CurvePoint> getCurvePoint(Integer id);

  /**
   * deleteCurvePoint. Method that delete given CurvePoint in database.
   *
   * @param curvePoint a CurvePoint
   */
  void deleteCurvePoint(CurvePoint curvePoint);
}
