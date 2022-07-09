package com.nnk.springboot.repository;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Tag("CurvePointRepositoryTest")
@DisplayName("curvePoint repository implementation class")
@SpringBootTest
public class CurvePointRepositoryTest {

  @Autowired
  private CurvePointRepository curvePointRepository;

  private CurvePoint curvePoint;

  @BeforeEach
  public void setUp() {
    curvePoint = new CurvePoint(10, 10d, 30d);
    curvePointRepository.save(curvePoint);
  }

  @Test
  @DisplayName("saveCurvePoint, should save a curvePoint")
  public void saveCurvePoint_ShouldSaveCurvePoint() {
    // GIVEN
    CurvePoint curve = new CurvePoint(20, 20d, 40d);

    // WHEN
    CurvePoint result = curvePointRepository.save(curve);

    // THEN
    Assert.assertNotNull(result.getId());
    Assert.assertEquals(result.getCurveId(), 20, 20);
  }

  @Test
  @DisplayName("getAllCurvePoint, should return curvePoint list")
  public void getAllCurvePoint_ShouldReturnCurvePointList() {
    // GIVEN
    // WHEN
    List<CurvePoint> listResult = curvePointRepository.findAll();

    // THEN
    Assert.assertTrue(listResult.size() > 0);
  }

  @Test
  @DisplayName("getOneCurvePoint, should return a curvePoint")
  public void getOneCurvePoint_ShouldReturnCurvePoint() {
    // GIVEN
    Integer id = curvePoint.getId();
    // WHEN
    Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);

    // THEN
    Assert.assertTrue(curvePoint.isPresent());

  }

  @Test
  @DisplayName("deleteCurvePoint, should delete a curvePoint")
  public void deleteCurvePoint_ShouldDeleteCurvePoint() {
    // GIVEN
    Integer id = curvePoint.getId();

    // WHEN
    curvePointRepository.delete(curvePoint);
    Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);

    // THEN
    Assert.assertFalse(curvePoint.isPresent());
  }

  @Test
  @DisplayName("updateCurvePoint, should update a curvePoint")
  public void updateCurvePoint_ShouldUpdateCurvePoint() {
    // GIVEN
    curvePoint.setTerm(40d);

    // WHEN
    curvePoint = curvePointRepository.save(curvePoint);

    // THEN
    Assert.assertEquals(curvePoint.getTerm(), 40d, 40d);
  }

}