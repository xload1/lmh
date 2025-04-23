package com.wspa.localmoviehub.functional;

import com.wspa.localmoviehub.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TheatreRepository extends JpaRepository<Theatre, Integer>, JpaSpecificationExecutor<Theatre> {
}
