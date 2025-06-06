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
        Note existing = Note.builder()
                .id(1L)
                .title("Old Title")
                .content("Old Content")
                .createdAt(LocalDateTime.now())
                .build();

        Note updated = Note.builder()
                .title("New Title")
                .content("New Content")
                .build();

        when(noteRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(noteRepository.save(any(Note.class))).thenAnswer(i -> i.getArgument(0));

        Note result = noteService.updateNote(1L, updated);

        assertEquals("New Title", result.getTitle());
        assertEquals("New Content", result.getContent());
    }

    @Test
    public void testDeleteNote() {
        Note note = Note.builder()
                .id(1L)
                .title("Title")
                .content("Content")
                .createdAt(LocalDateTime.now())
                .build();

        when(noteRepository.findById(eq(1L))).thenReturn(Optional.of(note));
        doNothing().when(noteRepository).deleteById(1L);

        noteService.deleteNote(1L);

        verify(noteRepository, times(1)).delete(note);
        verify(noteRepository, times(1)).findById(1L);
    }

}