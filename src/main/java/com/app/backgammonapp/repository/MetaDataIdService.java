package com.app.backgammonapp.repository;

import com.app.backgammonapp.data.MetaDataId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetaDataIdService {

    @Autowired
    private MetaDataIdRepository metaDataIdRepository;

    public MetaDataIdService(){}

    public void createMetaDataIdIndex(final MetaDataId metaDataId) {
        metaDataIdRepository.save(metaDataId);
    }

    public Optional<MetaDataId> getMetaDataId(String id) {
        Optional<MetaDataId> metaDataId = metaDataIdRepository.findById(id);
        return metaDataId;
    }
}
