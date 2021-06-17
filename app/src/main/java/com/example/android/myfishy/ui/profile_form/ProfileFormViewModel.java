package com.example.android.myfishy.ui.profile_form;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import com.example.android.myfishy.db.HealthyDatabase;
import com.example.android.myfishy.db.entities.User;
import com.example.android.myfishy.repo.HealthyRepository;
import org.jetbrains.annotations.NotNull;

public class ProfileFormViewModel extends AndroidViewModel {

    public static final String PROFILE_FORM_VIEW_MODEL_TAG =
            HealthyRepository.buildTag(HealthyRepository.PROFILE_TAG, HealthyRepository.VIEW_MODEL_TAG);

    private HealthyRepository healthyRepository;

    public ProfileFormViewModel(@NonNull @NotNull Application application) {
        super(application);
        healthyRepository = new HealthyRepository(application, PROFILE_FORM_VIEW_MODEL_TAG);
    }

    public void insertUser(User user) {
        healthyRepository.insertUser(user);
    }

}
