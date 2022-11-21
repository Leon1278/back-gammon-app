package com.app.backgammonapp.repository;

import com.app.backgammonapp.data.Entry;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    public EntryService() {
    }

    public void createEntryIndexBulk(final List<Entry> entries) {
        entryRepository.saveAll(entries);
    }

    public void createEntryIndex(final Entry entry) {
        setId(entry);
        entryRepository.save(entry);
    }

    private Entry setId(Entry entry) {
        entry.setId(UUID.randomUUID().toString());
        return entry;
    }

}
