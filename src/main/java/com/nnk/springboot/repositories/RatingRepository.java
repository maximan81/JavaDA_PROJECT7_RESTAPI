package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RatingRepository. Interface that allows to access and
 * persist data between Rating object and poseidenDb database
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
