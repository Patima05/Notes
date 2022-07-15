package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbarAndDrawer();

        if (savedInstanceState == null) {
            TitlesFragment titlesFragment = new TitlesFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, titlesFragment)
                    .commit();

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                RecordFragment recordFragment = new RecordFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_record, recordFragment)
                        .commit();
            }
        }
    }

    private void initToolbarAndDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initDrawer(toolbar);
    }

    @SuppressLint("NonConstantResourceId")
    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_tools:
                    showToolsFragment();
                    drawer.close();
                    return true;
                case R.id.action_about:
                    MenuMethods.showAboutOfApp(this);
                    drawer.close();
                    return true;
                case R.id.action_close:
                    MenuMethods.showCloseAlertDialog(this);
                    drawer.close();
                    return true;
                default: return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search: {
                showSearchFragment();
                return true;
            }
            case R.id.action_sort: {
                applySorting();
                return true;
            }
            case R.id.action_send: {
                Toast.makeText(this, "Поделиться", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.action_add_img: {
                Toast.makeText(this, "Добавить картинку", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSearchFragment() {
        boolean isShowSearchFragment = false;
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment: fragments){
            if (fragment instanceof SearchFragment){
                isShowSearchFragment = true;
            }
        }
        if (!isShowSearchFragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new SearchFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
    private void showToolsFragment() {
        boolean isShowToolsFragment = false;
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment: fragments){
            if (fragment instanceof ToolsFragment){
                isShowToolsFragment = true;
            }
        }
        if (!isShowToolsFragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ToolsFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
    private void applySorting() {
        Toast.makeText(this, "Результат сортировки", Toast.LENGTH_SHORT).show();
    }


}
