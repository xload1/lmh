package com.wspa.localmoviehub.functional;

import com.wspa.localmoviehub.entities.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;

    public List<Theatre> getNearbyTheatres(String city) {
        return theatreRepository.findAll();
    }
}
