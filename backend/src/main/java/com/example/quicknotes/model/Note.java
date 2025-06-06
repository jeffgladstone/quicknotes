package com.example.quicknotes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 1000, message = "Content must not exceed 1000 characters")
    private String content;

    @NotNull(message = "CreatedAt cannot be null")
    private LocalDateTime createdAt;
}