package com.example.android.myfishy.ui.create_meal;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import com.example.android.myfishy.R;
import com.example.android.myfishy.ui.add_nourishment.AddNourishmentFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreateMealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meal);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.activity_create_meal_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }

    public void searchForNourishment(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.create_meal_container);
        if (fragment == null) {
            fragment = new AddNourishmentFragment();
            fragmentManager.beginTransaction().add(R.id.create_meal_container, fragment).commit();
        }
    }
}