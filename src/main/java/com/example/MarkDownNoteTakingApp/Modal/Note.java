package com.example.MarkDownNoteTakingApp.Modal;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Lob
    private String markdownContent;

    @Lob
    private String htmlContent;

    private String fileName;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDateTime = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime modifiedDateTime = LocalDateTime.now();

    public Note(String title, String markdownContent, String htmlContent, String fileName) {
        this.title = title;
        this.markdownContent = markdownContent;
        this.htmlContent = htmlContent;
        this.fileName = fileName;
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedDateTime = LocalDateTime.now();
    }
}
