package com.example.notes;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.DatePicker;

public class Note implements Parcelable {
    private final Integer index;
    private String title;
    private String text;
    private String date;


    public Note(Integer index, String title) {
        this.index = index;
        this.title = title;
    }

    public Note(Integer index, String title, String text, String date) {
        this.index = index;
        this.title = title;
        this.text = text;
        this.date = date;
    }

    protected Note(Parcel in) {
        if (in.readByte() == 0) {
            index = null;
        } else {
            index = in.readInt();
        }
        title = in.readString();
        text = in.readString();
        date = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getIndex() {
        return index;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (index == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(index);
        }
        parcel.writeString(title);
        parcel.writeString(text);
        parcel.writeString(date);
    }
}
