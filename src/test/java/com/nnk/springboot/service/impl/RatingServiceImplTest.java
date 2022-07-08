package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
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
@Tag("RatingServiceImplTest")
@DisplayName("rating service implementation class")
public class RatingServiceImplTest {

  @Mock
  private RatingRepository ratingRepository;

  private RatingService ratingService;

  private Rating rating;

  @BeforeEach
  public void setUp() {
    ratingService = new RatingServiceImpl(ratingRepository);

    rating = rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
  }

  @Test
  @DisplayName("getAllRating, should return a list of rating")
  public void getAllRating_ShouldReturnListOfRating() {
    // GIVEN
    when(ratingRepository.findAll()).thenReturn(List.of(rating));

    // WHEN
    Iterable<Rating> results = ratingService.getRatings();

    // THEN
    verify(ratingRepository, times(1)).findAll();
    assertThat(results).contains(rating);
  }

  @Test
  @DisplayName("getOneRating, should return a rating")
  public void getOneRating_ShouldReturnRating() {
    // GIVEN
    Integer id = 1;
    when(ratingRepository.findById(id)).thenReturn(Optional.ofNullable(rating));

    // WHEN
    Optional<Rating> result = ratingService.getRating(id);

    // THEN
    verify(ratingRepository, times(1)).findById(id);
    AssertionsForClassTypes.assertThat(result.get()).isEqualTo(rating);
  }

  @Test
  @DisplayName("deleteRating, should delete a rating")
  public void deleteRating_ShouldDeleteRating() {
    // GIVEN
    doNothing().when(ratingRepository).delete(rating);

    // WHEN
    ratingService.deleteRating(rating);

    // THEN
    assertThat(ratingService.getRatings()).doesNotContain(rating);
  }

  @Test
  @DisplayName("saveRating, should save a rating")
  public void saveBidList() {
    // GIVEN
    when(ratingRepository.save(rating)).thenReturn(rating);

    // WHEN
    Rating result = ratingService.saveRating(rating);

    // THEN
    assertThat(result).isEqualTo(result);
  }
}