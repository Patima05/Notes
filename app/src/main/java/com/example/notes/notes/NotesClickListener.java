package com.example.notes.notes;

import com.example.notes.Note;

public interface NotesClickListener {
    void onImageClick(Note note);
    void onTextViewClick(int position);
}
