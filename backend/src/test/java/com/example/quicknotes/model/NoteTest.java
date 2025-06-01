package com.example.quicknotes.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {

    @Test
    public void testNoteBuilderAndGetters() {
        UUID id = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();

        Note note = Note.builder()
                .id(id)
                .title("Test Title")
                .content("Test Content")
                .createdAt(timestamp)
                .build();

        assertEquals(id, note.getId());
        assertEquals("Test Title", note.getTitle());
        assertEquals("Test Content", note.getContent());
        assertEquals(timestamp, note.getCreatedAt());
    }

    @Test
    public void testNoteSetters() {
        Note note = new Note();
        UUID id = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();

        note.setId(id);
        note.setTitle("Setter Title");
        note.setContent("Setter Content");
        note.setCreatedAt(timestamp);

        assertEquals(id, note.getId());
        assertEquals("Setter Title", note.getTitle());
        assertEquals("Setter Content", note.getContent());
        assertEquals(timestamp, note.getCreatedAt());
    }

    @Test
    public void testNoteEqualsAndHashCode() {
        UUID id = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();

        Note note1 = new Note(id, "Title", "Content", timestamp);
        Note note2 = new Note(id, "Title", "Content", timestamp);

        assertEquals(note1, note2);
        assertEquals(note1.hashCode(), note2.hashCode());
    }
}