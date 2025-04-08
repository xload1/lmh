package com.wspa.localmoviehub.functional;

import com.wspa.localmoviehub.entities.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MoviesRepository extends JpaRepository<Movies, Integer>, JpaSpecificationExecutor<Movies> {
    java.util.List<com.wspa.localmoviehub.entities.Movies> findByReleased(boolean released);
    //java.util.List<com.wspa.localmoviehub.entities.Movies> findByTitleContainingIgnoreCase(String title);
}
