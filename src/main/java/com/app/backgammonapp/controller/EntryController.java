package com.app.backgammonapp.controller;

import com.app.backgammonapp.data.Entry;
import com.app.backgammonapp.repository.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryController {

    @Autowired
    private EntryService entryService;

    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEntry(@RequestBody Entry entry)
    {
        entryService.createEntryIndex(entry);
        return null;
    }
}
