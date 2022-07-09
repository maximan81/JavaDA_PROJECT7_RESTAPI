package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * RatingServiceImpl. class that implement
 * Rating business logic
 */
@Service
public class RatingServiceImpl implements RatingService {

  private final RatingRepository ratingRepository;

  public RatingServiceImpl(RatingRepository ratingRepository) {
    this.ratingRepository = ratingRepository;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<Rating> getRatings() {
    return ratingRepository.findAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Rating saveRating(Rating rating) {
    return ratingRepository.save(rating);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Rating> getRating(Integer id) {
    return ratingRepository.findById(id);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteRating(Rating rating) {
    ratingRepository.delete(rating);
  }
}
