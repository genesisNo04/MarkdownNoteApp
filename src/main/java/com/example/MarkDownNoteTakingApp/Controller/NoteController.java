package com.example.MarkDownNoteTakingApp.Controller;

import com.example.MarkDownNoteTakingApp.DTO.GrammarIssueDTO;
import com.example.MarkDownNoteTakingApp.DTO.NoteDTO;
import com.example.MarkDownNoteTakingApp.Modal.Note;
import com.example.MarkDownNoteTakingApp.Service.Impl.GrammarCheckService;
import com.example.MarkDownNoteTakingApp.Service.Impl.MarkdownService;
import com.example.MarkDownNoteTakingApp.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/v1/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private MarkdownService markdownService;

    @Autowired
    private GrammarCheckService grammarCheckService;

    @PostMapping("/upload")
    public ResponseEntity<NoteDTO> createNewNoteWithUpload(@RequestParam("file") MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        String markdownContent = new String(file.getBytes(), StandardCharsets.UTF_8);
        markdownContent = markdownContent.replace("\\n", "\n");
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

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> retrieveNoteById(@PathVariable Long id) {
        Note note = noteService.getNoteById(id);
        NoteDTO response = new NoteDTO(note.getTitle(), note.getMarkdownContent(), note.getHtmlContent(), note.getCreatedDateTime(), note.getModifiedDateTime());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<NoteDTO>> retrieveAllNotes() {
        List<NoteDTO> notes = noteService.getAllNote().stream().map(note -> new NoteDTO(note.getTitle(), note.getMarkdownContent(), note.getHtmlContent(), note.getCreatedDateTime(), note.getModifiedDateTime())).toList();
        return ResponseEntity.ok(notes);
    }

    @PostMapping("/grammar")
    public ResponseEntity<List<GrammarIssueDTO>> createNote(@RequestParam("file") MultipartFile file) throws IOException {
        String markdownContent = new String(file.getBytes(), StandardCharsets.UTF_8);
        markdownContent = markdownContent.replace("\\n", "\n");

        try {
            var matches = grammarCheckService.checkGrammar(markdownContent);

            var issues = matches.stream()
                    .map(match -> new GrammarIssueDTO(
                            match.getFromPos(),
                            match.getToPos(),
                            match.getMessage(),
                            match.getSuggestedReplacements().isEmpty() ? null : match.getSuggestedReplacements().get(0)
                    )).toList();

            return ResponseEntity.ok(issues);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
