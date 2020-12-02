package com.first.rednotes.controllers;

import com.first.rednotes.dao.NoteRepo;
import com.first.rednotes.dao.UserRepo;
import com.first.rednotes.model.Note;
import com.first.rednotes.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/rednotes")
public class MainController {

    private final NoteRepo noteRepo;
    private final UserRepo userRepo;
    public MainController(NoteRepo noteRepo, UserRepo userRepo){
        this.noteRepo = noteRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("")
    public String main(){
        return "main";
    }

    @GetMapping("/notes")
    public String notesPage(Model model){
        Iterable<Note> notes = noteRepo.findAll();
        model.addAttribute("notes", notes);

        return "notes";
    }

    @PostMapping("/notes")
    public String addNote(
            @AuthenticationPrincipal User user,
            Model model,
            @RequestParam String text,
            @RequestParam String heading
    ){
        Note note = new Note(text, heading, user);
        noteRepo.save(note);
        return "redirect:/rednotes/notes";

    }
}
