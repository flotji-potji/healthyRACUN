package com.example.android.myfishy.ui.profile_form;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.myfishy.R;
import com.example.android.myfishy.repo.HealthyRepository;

public class ProfileFormActivity extends AppCompatActivity {

    public static final String PROFILE_FORM_ACTIVITY_TAG =
            HealthyRepository.buildTag(HealthyRepository.PROFILE_FORM_TAG, HealthyRepository.ACTIVITY_TAG);

    private ProfileFormViewModel profileFormViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_form);
        profileFormViewModel =
                new ViewModelProvider(this).get(ProfileFormViewModel.class);

    }

}