package com.first.rednotes.controllers;

import com.first.rednotes.dao.NoteRepo;
import com.first.rednotes.dao.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
    public String notesPage(){
        return "notes";
    }
}
