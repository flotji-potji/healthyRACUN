package com.example.android.myfishy.ui.profile_form;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.myfishy.R;
import com.example.android.myfishy.db.entities.User;
import com.example.android.myfishy.repo.HealthyRepository;

import java.util.Locale;

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
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_form);
        profileFormViewModel =
                new ViewModelProvider(this).get(ProfileFormViewModel.class);

        etUsername = findViewById(R.id.edittext_username);
        etFirstname = findViewById(R.id.edittext_firstname);
        etSurname = findViewById(R.id.edittext_surname);
        etHeight = findViewById(R.id.edittext_height);
        etWeight = findViewById(R.id.edittext_weight);
        spCondition = findViewById(R.id.spinner_condition);
        radioGroup = findViewById(R.id.profileForm_radioGroup);
    }

    private void toasting(String msg) {
        Toast.makeText(this, msg + " fehlt", Toast.LENGTH_SHORT).show();
    }

    public void createUser(View view) {
        if (etUsername.getText().toString().equals("")) {
            toasting(etUsername.getContentDescription().toString());
            return;
        } else if (etFirstname.getText().toString().equals("")) {
            toasting(etFirstname.getContentDescription().toString());
            return;
        } else if (etSurname.getText().toString().equals("")) {
            toasting(etSurname.getContentDescription().toString());
            return;
        } else if (etHeight.getText().toString().equals("")) {
            toasting(etHeight.getContentDescription().toString());
            return;
        } else if (etWeight.getText().toString().equals("")) {
            toasting(etWeight.getContentDescription().toString());
            return;
        } else if (!male.isSelected() && !female.isSelected()) {
            toasting("Geschlecht");
            return;
        }

        String sex = "";
        if (male.isSelected()) {
            sex = "male";
        } else if (female.isSelected()) {
            sex = "female";
        }
        profileFormViewModel.insertUser(
                new User(
                        etUsername.getText().toString().trim(),
                        etFirstname.getText().toString().trim(),
                        etSurname.getText().toString().trim(),
                        0,
                        sex,
                        Float.parseFloat(etWeight.getText().toString()),
                        Float.parseFloat(etHeight.getText().toString())
                )
        );
    }
}