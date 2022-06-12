package com.example.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class RecordFragment extends Fragment {

    static final String ARG_INDEX = "index";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_record, container,
                false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Note note = arguments.getParcelable(ARG_INDEX);
            TextView recordOfNote = view.findViewById(R.id.record_of_note_textview);
            String[] records = getResources().getStringArray(R.array.notesRecords);
            recordOfNote.setText(records[note.getIndex()]);
            TextView titleOfNote = view.findViewById(R.id.title_of_note_textview);
            titleOfNote.setText(note.getTitle());
        }
        Button btnBack = view.findViewById(R.id.title_of_note_button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    public static RecordFragment newInstance(Note note) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INDEX, note);
        fragment.setArguments(args);
        return fragment;
    }

}