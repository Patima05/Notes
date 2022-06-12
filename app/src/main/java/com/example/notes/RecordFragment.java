package com.example.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
            int index = arguments.getInt(ARG_INDEX);
            TextView recordOfNote = view.findViewById(R.id.record_of_note_textview);
            String[] records = getResources().getStringArray(R.array.notesRecords);
            recordOfNote.setText(records[index]);
        }
    }

    public static RecordFragment newInstance(int index) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

}