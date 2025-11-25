package com.example.MarkDownNoteTakingApp.Controller;

import com.example.MarkDownNoteTakingApp.DTO.NoteDTO;
import com.example.MarkDownNoteTakingApp.Modal.Note;
import com.example.MarkDownNoteTakingApp.Service.Impl.MarkdownService;
import com.example.MarkDownNoteTakingApp.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/v1/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private MarkdownService markdownService;

    @PostMapping("/upload")
    public ResponseEntity<NoteDTO> createNewNoteWithUpload(@RequestParam("file") MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        String markdownContent = new String(file.getBytes(), StandardCharsets.UTF_8);
        String htmlContent = markdownService.renderToHtml(markdownContent);

        Note note = new Note(fileName, markdownContent, htmlContent, fileName);
        noteService.createNote(note);

        NoteDTO response = new NoteDTO();
        response.setTitle(note.getTitle());
        response.setHtmlContent(htmlContent);
        response.setMarkdownContent(markdownContent);
        response.setCreatedTime(note.getCreatedDateTime());
        response.setModifiedTime(note.getModifiedDateTime());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@RequestBody NoteDTO noteDTO) {
        NoteDTO response = new NoteDTO();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
