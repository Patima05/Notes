package com.example.notes.notes;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.notes.Note;
import com.example.notes.R;
import com.example.notes.RecordFragment;
import com.example.notes.notes.NotesAdapter;

import java.util.ArrayList;

public class NotesFragment extends Fragment {

    private Note current_note = null;
    private final String CURRENT_NOTE = "CURRENT_NOTE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_titles, container, false);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null){
            current_note = savedInstanceState.getParcelable(CURRENT_NOTE);
        }
        initList(view);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem itemSend = menu.findItem(R.id.action_send);
        itemSend.setVisible(false);
        MenuItem itemAddImg = menu.findItem(R.id.action_add_img);
        itemAddImg.setVisible(false);
    }

    // создаём список заметок на экране из массива в ресурсах
    private void initList(View view) {
        ArrayList<Note> notes = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.notesTitles);
        TypedArray images = getResources().obtainTypedArray(R.array.notesImages);
        for (int i = 0; i < titles.length; i++) {
            Note note = new Note(i, titles[i], images.getResourceId(i,0));
            notes.add(note);
        }
        RecyclerView rv = view.findViewById(R.id.rv_notes);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        NotesAdapter adapter = new NotesAdapter();
        adapter.setList(notes);
        adapter.setListener(new NotesClickListener() {
            @Override
            public void onImageClick(Note note) {
                NotesFragment.this.current_note = note;
                //showImageChooseFragment();
            }

            @Override
            public void onTextViewClick(int position) {
                showRecord(notes.get(position));
            }
        });
        rv.setAdapter(adapter);
    }
    private void showRecord(Note note) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showRecordLand(note);
        } else {
            showRecordPortrait(note);
        }
    }

    private void showRecordPortrait(Note note) {
        RecordFragment recordFragment = RecordFragment.newInstance(note);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, recordFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    private void showRecordLand(Note note) {
        RecordFragment recordFragment = RecordFragment.newInstance(note);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_record, recordFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}