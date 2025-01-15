package me.kunalkayal.journalApp.controller;

import me.kunalkayal.journalApp.entity.JournalEntity;
import me.kunalkayal.journalApp.services.EntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journals")
public class journalController {
    @Autowired
    EntryService entryService;

    @GetMapping
    ResponseEntity<?> getUsersJournals() {
        List<JournalEntity> journals = entryService.getUsersJournals();
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }


    @PostMapping
    ResponseEntity<?> addJournal(@RequestBody JournalEntity j) {
        j.setDate(LocalDateTime.now());

        try {
            entryService.addJournal(j);
            return new ResponseEntity<>("Added Succesfully",HttpStatus.CREATED);
        } catch (Exception e) {
            return  new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping("/{id}")
    ResponseEntity<?> updateJournal(@RequestBody JournalEntity j,@PathVariable ObjectId id) {

            JournalEntity oldEntity = entryService.updateJournal(id,j);
            if(oldEntity!=null)return new ResponseEntity<>(oldEntity,HttpStatus.OK);

            return new ResponseEntity<>("Entry NOT FOUND",HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteJournal(@PathVariable ObjectId id) {
        if(entryService.deleteJournalById(id))return new ResponseEntity<>(HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
}
