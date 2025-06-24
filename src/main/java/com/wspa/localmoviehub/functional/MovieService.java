package com.wspa.localmoviehub.functional;

import com.wspa.localmoviehub.entities.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Movies> getTrendingInCity(String city) {
        return moviesRepository.findDistinctByCitiesContaining(city);
    }
    public Optional<Movies> getMovieById(int id) {
        return moviesRepository.findById(id);
    }

    public List<Movies> getUpcomingMoviesFiltered(String keyword,String genre) {
        Specification<Movies> specification = Specification.where(MoviesSpecifications.hasGenre(genre))
                .and(MoviesSpecifications.titleOrDescriptionContains(keyword))
                .and(MoviesSpecifications.isUpcoming());

        return moviesRepository.findAll(specification);
    }
}
