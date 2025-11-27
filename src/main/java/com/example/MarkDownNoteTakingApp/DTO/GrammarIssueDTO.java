package com.example.MarkDownNoteTakingApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GrammarIssueDTO {

    private int start;
    private int end;
    private String message;
    private String suggestion;
}
