package com.nnk.springboot.repository;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Tag("RatingRepositoryTest")
@DisplayName("raring repository implementation class")
@SpringBootTest
public class RatingRepositoryTest {

  @Autowired
  private RatingRepository ratingRepository;

  private Rating rating;

  @BeforeEach
  public void setUp() {
    rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
    ratingRepository.save(rating);
  }

  @Test
  @DisplayName("saveRating, should save a rating")
  public void saveRating_ShouldSaveRating() {
    // GIVEN
    Rating rat = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

    // WHEN
    Rating result = ratingRepository.save(rat);

    // THEN
    Assert.assertNotNull(result.getId());
    Assert.assertEquals(result.getFitchRating(), "Sand PRating", "Sand PRating");
  }

  @Test
  @DisplayName("getAllRating, should return rating list")
  public void getAllRating_ShouldReturnRatingList() {
    // GIVEN
    // WHEN
    List<Rating> listResult = ratingRepository.findAll();

    // THEN
    Assert.assertTrue(listResult.size() > 0);
  }

  @Test
  @DisplayName("getOneRating, should return a rating")
  public void getOneRating_ShouldReturnRating() {
    // GIVEN
    Integer id = rating.getId();
    // WHEN
    Optional<Rating> rating = ratingRepository.findById(id);

    // THEN
    Assert.assertTrue(rating.isPresent());

  }

  @Test
  @DisplayName("deleteRating, should delete a rating")
  public void deleteRating_ShouldDeleteRating() {
    // GIVEN
    Integer id = rating.getId();

    // WHEN
    ratingRepository.delete(rating);
    Optional<Rating> rating = ratingRepository.findById(id);

    // THEN
    Assert.assertFalse(rating.isPresent());
  }

  @Test
  @DisplayName("updateRating, should update a rating")
  public void updateRating_ShouldUpdateRating() {
    // GIVEN
    rating.setFitchRating("Sand PRating update");

    // WHEN
    rating = ratingRepository.save(rating);

    // THEN
    Assert.assertEquals(rating.getFitchRating(), "Sand PRating update", "Sand PRating update");
  }
  
}