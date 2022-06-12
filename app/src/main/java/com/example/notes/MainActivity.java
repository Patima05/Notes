package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Создаем фрагмент
        if (savedInstanceState == null) {
            TitlesFragment titlesFragment = new TitlesFragment();
            // Вызываем FragmentManager
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, titlesFragment)
                    .commit();

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                RecordFragment recordFragment = RecordFragment.newInstance(0);
                // Вызываем FragmentManager
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_record, recordFragment)
                        .commit();
            }
        }
    }

}
