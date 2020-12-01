package com.first.rednotes.dao;

import com.first.rednotes.model.Note;
import com.first.rednotes.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepo extends CrudRepository<Note, Long> {
    List<Note> findByHeading(String tag);
    List<Note> findByAuthor(User author);
}
