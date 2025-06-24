package com.wspa.localmoviehub.functional;

import com.wspa.localmoviehub.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
