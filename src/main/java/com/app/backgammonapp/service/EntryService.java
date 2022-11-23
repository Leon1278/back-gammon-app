package com.app.backgammonapp.service;

import com.app.backgammonapp.data.Entry;
import com.app.backgammonapp.repository.EntryRepository;
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

    public void saveToEntryIndex(final Entry entry) {
        setId(entry);
        entryRepository.save(entry);
    }

    private Entry setId(Entry entry) {
        entry.setId(UUID.randomUUID().toString());
        return entry;
    }

}
