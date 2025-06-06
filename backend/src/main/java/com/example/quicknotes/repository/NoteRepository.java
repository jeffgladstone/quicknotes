package com.example.quicknotes.repository;

import com.example.quicknotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {}