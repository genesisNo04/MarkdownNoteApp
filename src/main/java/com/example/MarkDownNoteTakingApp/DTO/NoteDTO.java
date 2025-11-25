package com.example.MarkDownNoteTakingApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {

    private String title;

    private String markdownContent;

    private String htmlContent;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedTime;
}
