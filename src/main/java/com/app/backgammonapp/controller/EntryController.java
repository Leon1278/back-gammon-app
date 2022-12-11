package com.app.backgammonapp.controller;

import com.app.backgammonapp.data.Entry;
import com.app.backgammonapp.data.Aggregation;
import com.app.backgammonapp.data.MetaDataId;
import com.app.backgammonapp.service.EntryService;
import com.app.backgammonapp.service.MetaDataIdService;
import com.app.backgammonapp.service.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class EntryController {

    @Autowired
    private EntryService entryService;

    @Autowired
    private AggregationService aggregationService;

    @Autowired
    private MetaDataIdService metaDataIdService;

    Logger logger = Logger.getLogger(EntryController.class.getName());

    @PostMapping(path= "/insert", consumes = "application/json", produces = "application/json")
    public void addEntry(@RequestBody Entry entry)
    {
        logger.setLevel(Level.ALL);
        try {
            entryService.saveToEntryIndex(entry);
            Optional<MetaDataId> metaDataId = metaDataIdService.getMetaDataId(entry.getGame());
            if (metaDataId.isPresent()) {
                logger.info("Found metaDataId data for game " + entry.getGame());
                MetaDataId metaDataIdObj = metaDataId.get();

                int id = metaDataIdObj.getMetaDataId();
                Optional<Aggregation> aggregation = aggregationService.getAggregationData(id, entry.getGame());
                if (aggregation.isPresent()) {
                    logger.info("Found aggregation data for game " + entry.getGame());

                    Aggregation aggregationObj = aggregation.get();

                    id++;
                    metaDataIdObj.setMetaDataId(id);
                    aggregationObj.setId(id);
                    aggregationObj.setTimestamp(entry.getTimestamp());

                    aggregationService.aggregate(aggregationObj, entry);
                    logger.info("New playerCount: " + aggregationObj.getPlayerCount().toString());

                    metaDataIdService.saveToMetaDataIdIndex(metaDataIdObj);
                    aggregationService.saveToAggregationIndex(aggregationObj);
                } else {
                    logger.info("A problem occured while fetching metaDataId or aggregation data for game " + entry.getGame());
                }
            } else {
                logger.info("No entries found for game " + entry.getGame());
                logger.info("New game will be created");

                MetaDataId metaDataIdObj = metaDataIdService.initialize(entry);
                Aggregation aggregationObj = aggregationService.initalize(entry);

                metaDataIdService.saveToMetaDataIdIndex(metaDataIdObj);
                aggregationService.saveToAggregationIndex(aggregationObj);

                logger.info("Created game " + entry.getGame() + " with player count: " + aggregationObj.getPlayerCount().toString());
            }
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }

    @PostMapping(path= "/delete", produces = "application/json")
    public void deleteEntry(String id) {
        entryService.deleteEntry(id);
        logger.info("Deleted entry with ID: " + id);
    }
}
