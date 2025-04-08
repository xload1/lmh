package com.wspa.localmoviehub.functional;

import com.wspa.localmoviehub.entities.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MoviesRepository moviesRepository;

    public List<Movies> getAllReleasedMovies() {
        return moviesRepository.findByReleased(true);
    }

    public List<Movies> getFilteredMovies(String genre, Integer minRating, Integer maxRating, String keyword) {
        Specification<Movies> specification = Specification.where(MoviesSpecifications.hasGenre(genre))
                .and(MoviesSpecifications.ratingGreaterOrEqualTo(minRating))
                .and(MoviesSpecifications.ratingLessOrEqualTo(maxRating))
                .and(MoviesSpecifications.titleOrDescriptionContains(keyword))
                .and(MoviesSpecifications.isReleased());

        return moviesRepository.findAll(specification);
    }
}
