package com.example.quicknotes.controller;

import com.example.quicknotes.model.Note;
import com.example.quicknotes.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllNotes() throws Exception {
        Note note = Note.builder()
                .title("Mock Note")
                .content("Mock content")
                .createdAt(LocalDateTime.now())
                .build();

        when(noteService.getAllNotes()).thenReturn(List.of(note));

        mockMvc.perform(get("/api/notes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Mock Note"));
    }

    @Test
    public void testCreateNote() throws Exception {
        Note note = Note.builder()
                .title("New Note")
                .content("Content")
                .build();

        when(noteService.createNote(any(Note.class))).thenReturn(note);

        mockMvc.perform(post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(note)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Note"))
                .andExpect(jsonPath("$.content").value("Content"));
    }

    @Test
    public void testUpdateNote() throws Exception {
        Note updated = Note.builder().title("Updated").content("Updated Content").build();

        when(noteService.updateNote(eq(1L), any(Note.class))).thenReturn(updated);

        mockMvc.perform(put("/api/notes/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated"))
                .andExpect(jsonPath("$.content").value("Updated Content"));
    }

    @Test
    public void testDeleteNote() throws Exception {
        doNothing().when(noteService).deleteNote(1L);

        mockMvc.perform(delete("/api/notes/{id}", 1L))
                .andExpect(status().isOk());

        verify(noteService, times(1)).deleteNote(1L);
    }

}