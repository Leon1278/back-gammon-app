package com.app.backgammonapp.service;

import com.app.backgammonapp.data.Entry;
import com.app.backgammonapp.data.MetaDataId;
import com.app.backgammonapp.repository.MetaDataIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetaDataIdService {

    @Autowired
    private MetaDataIdRepository metaDataIdRepository;

    public MetaDataIdService(){}

    public void saveToMetaDataIdIndex(final MetaDataId metaDataId) {
        metaDataIdRepository.save(metaDataId);
    }

    public Optional<MetaDataId> getMetaDataId(String id) {
        Optional<MetaDataId> metaDataId = metaDataIdRepository.findById(id);
        return metaDataId;
    }

    public MetaDataId initialize(Entry entry) {
        MetaDataId metaDataIdObj = new MetaDataId();
        metaDataIdObj.setId(entry.getGame());
        metaDataIdObj.setMetaDataId(0);
        return metaDataIdObj;
    }
}
