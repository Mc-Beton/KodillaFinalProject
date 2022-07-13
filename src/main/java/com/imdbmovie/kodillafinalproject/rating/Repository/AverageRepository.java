package com.imdbmovie.kodillafinalproject.rating.Repository;

import com.imdbmovie.kodillafinalproject.rating.domain.AverageRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface AverageRepository extends JpaRepository<AverageRating, Long> {

    AverageRating save(AverageRating rating);
    AverageRating findByMovieId(String Id);
}
