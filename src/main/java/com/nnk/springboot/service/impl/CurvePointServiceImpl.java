package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * CurvePointServiceImpl. class that implement
 * CurvePoint business logic
 */
@Service
public class CurvePointServiceImpl implements CurvePointService {
  private final CurvePointRepository curvePointRepository;

  public CurvePointServiceImpl(CurvePointRepository curvePointRepository) {
    this.curvePointRepository = curvePointRepository;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<CurvePoint> getCurvePoints() {
    return curvePointRepository.findAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
    return curvePointRepository.save(curvePoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<CurvePoint> getCurvePoint(Integer id) {
    return curvePointRepository.findById(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteCurvePoint(CurvePoint curvePoint) {
    curvePointRepository.delete(curvePoint);
  }
}
