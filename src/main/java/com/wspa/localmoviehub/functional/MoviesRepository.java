package com.wspa.localmoviehub.functional;

import com.wspa.localmoviehub.entities.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {
    java.util.List<com.wspa.localmoviehub.entities.Movies> findByReleased(boolean released);
    //java.util.List<com.wspa.localmoviehub.entities.Movies> findByTitleContainingIgnoreCase(String title);
}
