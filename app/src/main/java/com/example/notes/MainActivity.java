package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
                RecordFragment recordFragment = new RecordFragment();
                // Вызываем FragmentManager
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_record, recordFragment)
                        .commit();
            }

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_about: {
                showAboutFragment();
                return true;
            }
            case R.id.action_exit: {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAboutFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new AboutFragment())
                .addToBackStack(null)
                .commit();
    }


}
