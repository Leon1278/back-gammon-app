package com.app.backgammonapp.controller;

import com.app.backgammonapp.data.Entry;
import com.app.backgammonapp.data.Aggregation;
import com.app.backgammonapp.data.MetaDataId;
import com.app.backgammonapp.repository.EntryService;
import com.app.backgammonapp.repository.MetaDataIdService;
import com.app.backgammonapp.repository.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private AggregationService aggregationService;

    @Autowired
    private MetaDataIdService metaDataIdService;

    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEntry(@RequestBody Entry entry)
    {
        entryService.createEntryIndex(entry);
        try {
            // get metaDataId
            Optional<MetaDataId> metaDataId = metaDataIdService.getMetaDataId(entry.getGame());
            if (metaDataId.isPresent()) {
                MetaDataId metaDataIdObj = metaDataId.get();

                // get metadata
                int id = metaDataIdObj.getMetaDataId();
                Optional<Aggregation> metaData = aggregationService.getAggregationData(id, entry.getGame());
                Aggregation aggregationObj = metaData.get();

                // increase id and set TS
                id = id + 1;
                metaDataIdObj.setMetaDataId(id);
                aggregationObj.setId(id);
                aggregationObj.setTimestamp(entry.getTimestamp());

                // map points to playerCount
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

                // save documents
                metaDataIdService.createMetaDataIdIndex(metaDataIdObj);
                aggregationService.createAggregationIndex(aggregationObj);

            } else {
                MetaDataId metaDataIdObj = new MetaDataId();
                Aggregation aggregationObj = new Aggregation();

                metaDataIdObj.setId(entry.getGame());
                metaDataIdObj.setMetaDataId(0);

                aggregationObj.setId(0);
                aggregationObj.setGame(entry.getGame());
                aggregationObj.setTimestamp(entry.getTimestamp());

                HashMap<String, Float> points = entry.getPoints();
                HashMap<String, Float> playerCount = points;
                aggregationObj.setPlayerCount(playerCount);

                metaDataIdService.createMetaDataIdIndex(metaDataIdObj);
                aggregationService.createAggregationIndex(aggregationObj);
            }
        } catch(Exception e) {
    }
        return null;
    }
}
