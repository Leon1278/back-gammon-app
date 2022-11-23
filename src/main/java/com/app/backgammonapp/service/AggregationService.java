package com.app.backgammonapp.service;

import com.app.backgammonapp.controller.EntryController;
import com.app.backgammonapp.data.Aggregation;
import com.app.backgammonapp.data.Entry;
import com.app.backgammonapp.repository.AggregationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AggregationService {

    @Autowired
    private AggregationRepository aggregationRepository;

    Logger logger = Logger.getLogger(EntryController.class.getName());

    public AggregationService() {
        logger.setLevel(Level.ALL);
    }

    public void saveToAggregationIndex(final Aggregation aggregation) {
        aggregationRepository.save(aggregation);
    }

    public Optional<Aggregation> getAggregationData(int id, String game) {
        Optional<Aggregation> aggregation = aggregationRepository.findByIdAndGame(id, game);
        return aggregation;
    }

    // map points to playerCount
    public Aggregation aggregate(Aggregation aggregationObj, Entry entry) {
        HashMap<String, Float> playerCount = aggregationObj.getPlayerCount();
        HashMap<String, Float> points = entry.getPoints();
        for (String key : points.keySet()) {
            if (playerCount.containsKey(key)) {
                Float oldValue = playerCount.get(key);
                Float newValue = oldValue + points.get(key);
                playerCount.put(key, newValue);
            } else {
                playerCount.put(key, points.get(key));
            }
        }
        return aggregationObj;
    }

    public Aggregation initalize(Entry entry) {
        Aggregation aggregationObj = new Aggregation();
        aggregationObj.setId(0);
        aggregationObj.setGame(entry.getGame());
        aggregationObj.setTimestamp(entry.getTimestamp());
        HashMap<String, Float> points = entry.getPoints();
        HashMap<String, Float> playerCount = points;
        aggregationObj.setPlayerCount(playerCount);
        return aggregationObj;
    }
}
