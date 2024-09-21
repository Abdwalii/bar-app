package com.example.ayubapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BottomAppBar bottomAppBar;
    private FloatingActionButton fab, fabOption1, fabOption2;
    private androidx.appcompat.widget.SearchView searchView;
    private boolean isFabOpen = false; // Track FAB state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.bottom_app_bar);
        fab = findViewById(R.id.fab);
        fabOption1 = findViewById(R.id.fab_option_1);
        fabOption2 = findViewById(R.id.fab_option_2);
        searchView = findViewById(R.id.search_view);
        ImageButton searchIcon = findViewById(R.id.search_icon);

        setSupportActionBar(bottomAppBar);
        getSupportActionBar().setTitle("Your App Name");

        // Initially hide additional FAB options
        fabOption1.setVisibility(View.GONE);
        fabOption2.setVisibility(View.GONE);

        // Initially hide the SearchView
        searchView.setVisibility(View.GONE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFab(); // Toggle FAB and show/hide other buttons
            }
        });

        bottomAppBar.setOnMenuItemClickListener(new BottomAppBar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_menu) {
                    showPopupMenu(bottomAppBar); // Use BottomAppBar as the anchor
                    return true;
                }
                return false;
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSearchView();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchView.setVisibility(View.GONE);
                searchView.setQuery("", false);
                return true;
            }
        });
    }

    private void toggleFab() {
        if (isFabOpen) {
            fab.setImageResource(android.R.drawable.ic_input_add); // Change to '+'
            fabOption1.setVisibility(View.GONE); // Hide additional buttons
            fabOption2.setVisibility(View.GONE);
        } else {
            fab.setImageResource(android.R.drawable.ic_menu_close_clear_cancel); // Change to 'X'
            fabOption1.setVisibility(View.VISIBLE); // Show additional buttons
            fabOption2.setVisibility(View.VISIBLE);
        }
        isFabOpen = !isFabOpen;
    }

    private void showPopupMenu(View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.show();
    }

    private void toggleSearchView() {
        if (searchView.getVisibility() == View.GONE) {
            searchView.setVisibility(View.VISIBLE);
            searchView.requestFocus(); // Optionally focus the search view
        } else {
            searchView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bottom_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}