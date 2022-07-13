package com.imdbmovie.kodillafinalproject.rating.Repository;

import com.imdbmovie.kodillafinalproject.rating.domain.AverageRating;
import com.imdbmovie.kodillafinalproject.rating.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Rating save(Rating rating);
    List<Rating> findAll();
    Optional<List<Rating>> findAllByMovieId(String movieId);
    Optional<Rating> findByMovieIdAndAndUser_Username(String movieId, String username);
    void deleteById(Long id);
    Optional<Rating> findById(Long id);
}
