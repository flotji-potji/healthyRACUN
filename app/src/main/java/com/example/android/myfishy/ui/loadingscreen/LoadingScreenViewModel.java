package com.example.android.myfishy.ui.loadingscreen;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.android.myfishy.db.entities.User;
import com.example.android.myfishy.repo.HealthyRepository;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoadingScreenViewModel extends AndroidViewModel {

    public static final String LOADING_SCREEN_VIEW_MODEL_TAG =
            HealthyRepository.buildTag(HealthyRepository.LOADING_SCREEN_TAG, HealthyRepository.VIEW_MODEL_TAG);

    private HealthyRepository healthyRepository;

    public LoadingScreenViewModel(@NonNull @NotNull Application application) {
        super(application);
        healthyRepository = new HealthyRepository(application, LOADING_SCREEN_VIEW_MODEL_TAG);
    }

    public LiveData<List<User>> getCurrentUsers(){
        return healthyRepository.getUsers();
    }
}
