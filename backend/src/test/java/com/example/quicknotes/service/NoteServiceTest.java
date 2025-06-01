package com.example.quicknotes.service;

import com.example.quicknotes.model.Note;
import com.example.quicknotes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllNotes() {
        Note note = Note.builder()
                .title("Test Note")
                .content("This is a test note")
                .createdAt(LocalDateTime.now())
                .build();
        when(noteRepository.findAll()).thenReturn(Collections.singletonList(note));

        List<Note> result = noteService.getAllNotes();

        assertEquals(1, result.size());
        assertEquals("Test Note", result.get(0).getTitle());
    }

    @Test
    public void testCreateNote() {
        Note note = Note.builder()
                .title("New Note")
                .content("This is a new note")
                .build();
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        Note result = noteService.createNote(note);

        assertEquals("New Note", result.getTitle());
        verify(noteRepository, times(1)).save(any(Note.class));
    }

    @Test
    public void testUpdateNote() {
        UUID id = UUID.randomUUID();
        Note existing = Note.builder()
                .id(id)
                .title("Old Title")
                .content("Old Content")
                .createdAt(LocalDateTime.now())
                .build();

        Note updated = Note.builder()
                .title("New Title")
                .content("New Content")
                .build();

        when(noteRepository.findById(id)).thenReturn(Optional.of(existing));
        when(noteRepository.save(any(Note.class))).thenAnswer(i -> i.getArgument(0));

        Note result = noteService.updateNote(id, updated);

        assertEquals("New Title", result.getTitle());
        assertEquals("New Content", result.getContent());
    }

    @Test
    public void testDeleteNote() {
        UUID id = UUID.randomUUID();
        doNothing().when(noteRepository).deleteById(id);

        noteService.deleteNote(id);

        verify(noteRepository, times(1)).deleteById(id);
    }

}