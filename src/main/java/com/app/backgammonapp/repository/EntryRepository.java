package com.app.backgammonapp.repository;

import com.app.backgammonapp.data.Entry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EntryRepository extends ElasticsearchRepository<Entry, String> {
}
