package com.example.quicknotes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {

    @Test
    public void testNoteBuilderAndGetters() {
        LocalDateTime timestamp = LocalDateTime.now();

        Note note = Note.builder()
                .id(1L)
                .title("Test Title")
                .content("Test Content")
                .createdAt(timestamp)
                .build();

        assertEquals(1L, note.getId());
        assertEquals("Test Title", note.getTitle());
        assertEquals("Test Content", note.getContent());
        assertEquals(timestamp, note.getCreatedAt());
    }

    @Test
    public void testNoteSetters() {
        Note note = new Note();
        LocalDateTime timestamp = LocalDateTime.now();

        note.setId(1L);
        note.setTitle("Setter Title");
        note.setContent("Setter Content");
        note.setCreatedAt(timestamp);

        assertEquals(1L, note.getId());
        assertEquals("Setter Title", note.getTitle());
        assertEquals("Setter Content", note.getContent());
        assertEquals(timestamp, note.getCreatedAt());
    }

    @Test
    public void testNoteEqualsAndHashCode() {
        LocalDateTime timestamp = LocalDateTime.now();

        Note note1 = new Note(1L, "Title", "Content", timestamp);
        Note note2 = new Note(1L, "Title", "Content", timestamp);

        assertEquals(note1, note2);
        assertEquals(note1.hashCode(), note2.hashCode());
    }
}