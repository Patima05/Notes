package com.example.notes;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitlesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_titles, container, false);
        //TextView textView = rootView.findViewById(R.id.some_id);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    // создаём список заметок на экране из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.notesTitles);
        for (int i = 0; i < notes.length; i++) {
            String note = notes[i];
            TextView tv = new TextView(getContext());
            tv.setText(note);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int position = i;
            tv.setOnClickListener(v -> {
                showRecord(position);
            });

        }
    }
    private void showRecord(int index) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showRecordLand(index);
        } else {
            showRecordPortrait(index);
        }
    }

    private void showRecordPortrait(int index) {
        RecordFragment recordFragment = RecordFragment.newInstance(index);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, recordFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    private void showRecordLand(int index) {
        RecordFragment recordFragment = RecordFragment.newInstance(index);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_record, recordFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}