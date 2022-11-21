package com.app.backgammonapp.repository;

import com.app.backgammonapp.data.MetaDataId;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MetaDataIdRepository extends ElasticsearchRepository<MetaDataId, String> {
}
