package com.example.android.myfishy.ui.profile_form;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.myfishy.R;
import com.example.android.myfishy.repo.HealthyRepository;

public class ProfileFormActivity extends AppCompatActivity {

    public static final String PROFILE_FORM_ACTIVITY_TAG =
            HealthyRepository.buildTag(HealthyRepository.PROFILE_FORM_TAG, HealthyRepository.ACTIVITY_TAG);

    private ProfileFormViewModel profileFormViewModel;

    private EditText etUsername;
    private EditText etFirstname;
    private EditText etSurname;
    private EditText etHeight;
    private EditText etWeight;
    private Spinner spCondition;
    private RadioButton male;
    private RadioButton female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_form);
        profileFormViewModel =
                new ViewModelProvider(this).get(ProfileFormViewModel.class);

        etUsername = (EditText) findViewById(R.id.edittext_username);
        etFirstname = (EditText) findViewById(R.id.edittext_firstname);
        etSurname = (EditText) findViewById(R.id.edittext_surname);
        etHeight = (EditText) findViewById(R.id.edittext_height);
        etWeight = (EditText) findViewById(R.id.edittext_weight);
        spCondition = (Spinner) findViewById(R.id.spinner_condition);

    }

}