package com.nnk.springboot.service.impl;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.BidListService;
import com.nnk.springboot.service.CurvePointService;
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
@Tag("CurvePointServiceImplTest")
@DisplayName("curvePoint service implementation class")
public class CurvePointServiceImplTest {

  @Mock
  private CurvePointRepository curvePointRepository;

  private CurvePointService curvePointService;

  private CurvePoint curvePoint;

  @BeforeEach
  public void setUp() {
    curvePointService = new CurvePointServiceImpl(curvePointRepository);

    curvePoint = new CurvePoint(10, 10d, 30d);
  }

  @Test
  @DisplayName("getAllCurvePointList, should return a list of curvePoint")
  public void getAllCurvePointList_ShouldReturnListOfCurvePoint() {
    // GIVEN
    when(curvePointRepository.findAll()).thenReturn(List.of(curvePoint));

    // WHEN
    Iterable<CurvePoint> results = curvePointService.getCurvePoints();

    // THEN
    verify(curvePointRepository, times(1)).findAll();
    assertThat(results).contains(curvePoint);
  }

  @Test
  @DisplayName("getOneCurvePoint, should return a curvePoint")
  public void getOneCurvePoint_ShouldReturnCurvePoint() {
    // GIVEN
    Integer id = 1;
    when(curvePointRepository.findById(id)).thenReturn(Optional.ofNullable(curvePoint));

    // WHEN
    Optional<CurvePoint> result = curvePointService.getCurvePoint(id);

    // THEN
    verify(curvePointRepository, times(1)).findById(id);
    AssertionsForClassTypes.assertThat(result.get()).isEqualTo(curvePoint);
  }

  @Test
  @DisplayName("deleteCurvePoint, should delete a curvePoint")
  public void deleteCurvePoint_ShouldDeleteCurvePoint() {
    // GIVEN
    doNothing().when(curvePointRepository).delete(curvePoint);

    // WHEN
    curvePointService.deleteCurvePoint(curvePoint);

    // THEN
    assertThat(curvePointService.getCurvePoints()).doesNotContain(curvePoint);
  }

  @Test
  @DisplayName("saveCurvePoint, should save a curvePoint")
  public void saveCurvePoint_ShouldAddNewCurvePoint() {
    // GIVEN
    when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);

    // WHEN
    CurvePoint result = curvePointService.saveCurvePoint(curvePoint);

    // THEN
    AssertionsForClassTypes.assertThat(result).isEqualTo(curvePoint);
  }
}