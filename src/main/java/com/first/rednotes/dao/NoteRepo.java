package com.first.rednotes.dao;

import com.first.rednotes.model.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepo extends CrudRepository<Note, Long> {
}
