package com.app.backgammonapp.repository;

import com.app.backgammonapp.data.Aggregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AggregationService {

    @Autowired
    private AggregationRepository aggregationRepository;

    public AggregationService() {
    }

    public void createAggregationIndex(final Aggregation aggregation) {
        aggregationRepository.save(aggregation);
    }

    public Optional<Aggregation> getAggregationData(int id, String game) {
        Optional<Aggregation> aggregation = aggregationRepository.findByIdAndGame(id, game);
        return aggregation;
    }
}
