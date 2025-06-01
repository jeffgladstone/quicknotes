package com.example.quicknotes.service;

import com.example.quicknotes.model.Note;
import com.example.quicknotes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class NoteService {
    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    public Note createNote(Note note) {
        note.setCreatedAt(LocalDateTime.now());
        return repository.save(note);
    }

    public Note updateNote(UUID id, Note updatedNote) {
        return repository.findById(id)
                .map(existingNote -> {
                    existingNote.setTitle(updatedNote.getTitle());
                    existingNote.setContent(updatedNote.getContent());
                    return repository.save(existingNote);
                })
                .orElseThrow(() -> new NoSuchElementException("Note not found"));
    }

    public void deleteNote(UUID id) {
        repository.deleteById(id);
    }

}