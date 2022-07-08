package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;

import java.util.Optional;

/**
 * RatingService interface structure the business logic
 * of Rating.
 */
public interface RatingService {

  /**
   * getRatings. Method that get all Rating from database.
   *
   * @return Iterable Rating
   */
  Iterable<Rating> getRatings();

  /**
   * saveRating. Method that save given Rating in database.
   *
   * @param rating an Rating
   * @return an Rating
   */
  Rating saveRating(Rating rating);

  /**
   * getRating. Method that get one Rating from database.
   *
   * @param id an Rating id
   * @return optional Rating
   */
  Optional<Rating> getRating(Integer id);

  /**
   * deleteRating. Method that delete given Rating in database.
   *
   * @param rating a Rating
   */
  void deleteRating(Rating rating);
}
