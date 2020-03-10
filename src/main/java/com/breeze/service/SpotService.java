package com.breeze.service;

import com.breeze.pojo.Spot;
import org.springframework.data.domain.Page;

public interface SpotService {
    void saveSpot(Spot spot);

    void updateSpot(Spot spot);

    Page<Spot> findSpot(Spot spot);

    void delSpot(String ids);

    Spot findSpotById(String id);
}
