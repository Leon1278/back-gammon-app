package com.app.backgammonapp.repository;

import com.app.backgammonapp.data.Aggregation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface AggregationRepository extends ElasticsearchRepository<Aggregation, Integer> {

    Optional<Aggregation> findByIdAndGame(int id, String game);
}
