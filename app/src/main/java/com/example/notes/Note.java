package com.example.notes;

import android.widget.DatePicker;

public class Note {
    private String title;
    private String text;
    private DatePicker date;


    public Note(String title, String text, DatePicker date) {
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public DatePicker getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(DatePicker date) {
        this.date = date;
    }
}
