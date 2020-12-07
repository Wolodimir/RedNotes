package com.first.rednotes.controllers;

import com.first.rednotes.dao.NoteRepo;
import com.first.rednotes.dao.UserRepo;
import com.first.rednotes.model.Note;
import com.first.rednotes.model.User;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Optional;


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

    @GetMapping("/notes/{id}")
    public String findOneNote(@PathVariable(value = "id")Integer id, Model model){
        if(!noteRepo.existsById(id)){
            return "redirect:/rednotes/notes";
        }
        Optional<Note> note = noteRepo.findById(id);
        ArrayList<Note> n = new ArrayList<>();
        note.ifPresent(n::add);
        model.addAttribute("note", n);
        return "edit";
    }

    @PostMapping("/notes/{id}")
    public String updateNote(@PathVariable(value = "id") Integer id, @RequestParam String heading,
                             @RequestParam String text){
        Optional<Note> note = noteRepo.findById(id);
        ArrayList<Note> n = new ArrayList<>();
        note.ifPresent(n::add);
        Note ng = n.get(n.size()-1);
        ng.setHeading(heading);
        ng.setText(text);
        noteRepo.save(ng);
        return "redirect:/rednotes/notes";
    }
    @GetMapping("/notes/{id}/delete")
    public String deleteNote(@PathVariable(value = "id") Integer id){
        Optional<Note> note = noteRepo.findById(id);
        ArrayList<Note> n = new ArrayList<>();
        note.ifPresent(n::add);
        Note ng = n.get(n.size()-1);
        noteRepo.delete(ng);
        return "redirect:/rednotes/notes";
    }

}
