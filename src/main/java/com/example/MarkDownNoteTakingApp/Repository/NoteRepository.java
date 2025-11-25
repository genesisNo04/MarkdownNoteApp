package com.example.MarkDownNoteTakingApp.Repository;

import com.example.MarkDownNoteTakingApp.Modal.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
