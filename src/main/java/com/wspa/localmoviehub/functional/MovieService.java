package com.wspa.localmoviehub.functional;

import com.wspa.localmoviehub.entities.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MoviesRepository moviesRepository;

    public List<Movies> getAllReleasedMovies() {
        return moviesRepository.findByReleased(true);
    }
}
