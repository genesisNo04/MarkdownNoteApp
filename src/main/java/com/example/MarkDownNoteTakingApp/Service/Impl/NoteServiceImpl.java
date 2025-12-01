package com.example.MarkDownNoteTakingApp.Service.Impl;

import com.example.MarkDownNoteTakingApp.Exception.NoResourceFound;
import com.example.MarkDownNoteTakingApp.Modal.Note;
import com.example.MarkDownNoteTakingApp.Repository.NoteRepository;
import com.example.MarkDownNoteTakingApp.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note updateNote(Note note) {
        return null;
    }

    @Override
    public void deleteNote(Long id) {

    }

    @Override
    public Note getNoteById(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new NoResourceFound("Note does not exist"));
        return note;
    }

    @Override
    public List<Note> getAllNote() {
        return noteRepository.findAll();
    }
}
