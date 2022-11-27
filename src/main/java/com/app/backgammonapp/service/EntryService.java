package com.app.backgammonapp.service;

import com.app.backgammonapp.controller.EntryController;
import com.app.backgammonapp.data.Entry;
import com.app.backgammonapp.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    Logger logger = Logger.getLogger(EntryService.class.getName());

    public EntryService() {
    }

    public void createEntryIndexBulk(final List<Entry> entries) {
        entryRepository.saveAll(entries);
    }

    public void saveToEntryIndex(final Entry entry) {
        setId(entry);
        try {
            entryRepository.save(entry);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }

    private Entry setId(Entry entry) {
        entry.setId(UUID.randomUUID().toString());
        return entry;
    }

    public void deleteEntry(String id) {
        try {
            entryRepository.deleteById(id);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }
}
