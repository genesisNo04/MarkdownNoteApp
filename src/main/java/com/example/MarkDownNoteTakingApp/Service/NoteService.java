package com.example.MarkDownNoteTakingApp.Service;

import com.example.MarkDownNoteTakingApp.Modal.Note;

import java.util.List;

public interface NoteService {

    Note createNote(Note note);

    Note updateNote(Note note);

    void deleteNote(Long id);

    Note getNoteById(Long id);

    List<Note> getAllNote();
}
