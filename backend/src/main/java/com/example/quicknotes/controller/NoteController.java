package com.example.quicknotes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.quicknotes.model.Note;
import com.example.quicknotes.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
@Tag(name = "Notes API", description = "Endpoints for managing notes")
public class NoteController {

    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all notes")
    public List<Note> getAll() {
        logger.info("Fetching all notes");
        return service.getAllNotes();
    }

    @PostMapping
    @Operation(summary = "Create a new note")
    public Note create(@RequestBody Note note) {
        logger.info("Creating a new note with title: {}", note.getTitle());
        return service.createNote(note);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing note")
    public Note update(@PathVariable UUID id, @RequestBody Note updatedNote) {
        logger.info("Updating note with ID: {} and title: {}", id, updatedNote.getTitle());
        return service.updateNote(id, updatedNote);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a note by ID")
    public void delete(@PathVariable UUID id) {
        logger.info("Deleting note with ID: {}", id);
        service.deleteNote(id);
    }

}