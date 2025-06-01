package com.example.quicknotes.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String content;
    private LocalDateTime createdAt;
}