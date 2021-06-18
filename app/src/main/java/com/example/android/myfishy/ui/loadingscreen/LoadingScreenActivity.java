package com.example.android.myfishy.ui.loadingscreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.myfishy.MainActivity;
import com.example.android.myfishy.ui.profile_form.ProfileFormActivity;

public class LoadingScreenActivity extends AppCompatActivity {

    private LoadingScreenViewModel loadingScreenViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingScreenViewModel =
                new ViewModelProvider(this).get(LoadingScreenViewModel.class);
        loadingScreenViewModel.getCurrentUsers().observe(this, thing -> {
            Intent intent;

            if (thing.isEmpty())
                intent = new Intent(this, ProfileFormActivity.class);
            else
                intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }
}
