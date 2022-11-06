package com.app.backgammonapp.repository;

import com.app.backgammonapp.data.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;

    public void createEntryIndexBulk(final List<Entry> entries) {
        entryRepository.saveAll(entries);
    }

    public void createEntryIndex(final Entry entry) {
        entryRepository.save(entry);
    }
}
