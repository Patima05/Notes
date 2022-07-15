package com.example.notes;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class RecordFragment extends Fragment {

    static final String ARG_INDEX = "index";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_record, container,
                false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem itemSort = menu.findItem(R.id.action_sort);
        itemSort.setVisible(false);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        itemSearch.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_send){
            Toast.makeText(getContext(), "Поделиться с друзьями", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle
            savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        TextView tw = view.findViewById(R.id.record_of_note_textview);
        if (arguments != null) {
            Note note = arguments.getParcelable(ARG_INDEX);
            TextView recordOfNote = view.findViewById(R.id.record_of_note_textview);
            String[] records = getResources().getStringArray(R.array.notesRecords);
            recordOfNote.setText(records[note.getIndex()]);
            TextView titleOfNote = view.findViewById(R.id.title_of_note_textview);
            titleOfNote.setText(note.getTitle());
        }
        Button btnBack = view.findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
        initPopup(tw);
    }

    private void initPopup(TextView tw) {
        tw.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Activity activity = requireActivity();
                PopupMenu popupMenu = new PopupMenu(activity, view);
                activity.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_popup_clear: {
                                tw.setText("");
                                return true;
                            }
                            case R.id.action_popup_exit: {
                                MenuMethods.showCloseAlertDialog(requireActivity());
                                return true;
                            }
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return true;
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