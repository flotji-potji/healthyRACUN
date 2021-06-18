package com.example.android.myfishy.ui.profile_form;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.myfishy.MainActivity;
import com.example.android.myfishy.R;
import com.example.android.myfishy.db.entities.Diet;
import com.example.android.myfishy.db.entities.DietaryRestrictionTable;
import com.example.android.myfishy.db.entities.User;
import com.example.android.myfishy.repo.HealthyRepository;

import java.util.List;
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
        male = findViewById(R.id.radioButton_male);
        female = findViewById(R.id.radioButton_female);
    }

    private void toasting(String msg) {
        Toast.makeText(this, msg + " fehlt", Toast.LENGTH_SHORT).show();
    }

    public void createUser(View view) {
        if (etUsername == null) {
            toasting("Username");
            return;
        } else if (etFirstname == null) {
            toasting("Vorname");
            return;
        } else if (etSurname == null) {
            toasting("Nachname");
            return;
        } else if (etHeight == null) {
            toasting("Größe");
            return;
        } else if (etWeight == null) {
            toasting("Gewicht");
            return;
        } else if (male == null && female == null) {
            toasting("Geschlecht");
            return;
        }

        String sex = "";
        if (male.isChecked()) {
            sex = "male";
        } else if (female.isChecked()) {
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
        Log.e(PROFILE_FORM_ACTIVITY_TAG, spCondition.getSelectedItemPosition() + "");

        profileFormViewModel.getDietaryRestrictionTable().observe(this, dietaryRestrictionTables -> {
            DietaryRestrictionTable drt = dietaryRestrictionTables.get(spCondition.getSelectedItemPosition());
            profileFormViewModel.insertDiet(
                    new Diet(
                            drt.getDiet_plan_id(),
                            etUsername.getText().toString().trim(),
                            drt.getCondition_name(),
                            drt.getDiet_name(),
                            drt.getTable_salt(),
                            drt.getSodium(),
                            drt.getPotassium(),
                            drt.getCalcium(),
                            drt.getPhosphor(),
                            drt.getProtein(),
                            drt.getCalories(),
                            drt.getLiquid_intake(),
                            drt.getCarbs(),
                            drt.getFats(),
                            drt.getFibers()
                    )
            );
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}