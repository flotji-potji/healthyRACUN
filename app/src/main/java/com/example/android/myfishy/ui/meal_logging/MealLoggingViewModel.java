package com.example.android.myfishy.ui.meal_logging;

import android.app.Application;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.*;
import com.example.android.myfishy.db.entities.NutritionFactTable;
import com.example.android.myfishy.db.relations.UserGotDiets;
import com.example.android.myfishy.repo.HealthyRepository;

import java.util.List;

public class MealLoggingViewModel extends AndroidViewModel {

    public static final String MEAL_LOGGING_VIEW_MODEL_TAG =
            HealthyRepository.buildTag(HealthyRepository.MEAL_LOGGING_TAG, HealthyRepository.VIEW_MODEL_TAG);

    private HealthyRepository healthyRepository;
    private MutableLiveData<String> mText;
    private LiveData<List<String>> nourishmentNamesFromNutritionFactTable;
    private LiveData<List<NutritionFactTable>> nutritionFactTable;
    private LiveData<List<UserGotDiets>> userJoinsDiet;

    public MealLoggingViewModel(@NonNull Application application) {
        super(application);
        healthyRepository = new HealthyRepository(application, MEAL_LOGGING_VIEW_MODEL_TAG);
        nourishmentNamesFromNutritionFactTable = healthyRepository.getNourishmentNamesFromNutritionFactTable();
        nutritionFactTable = healthyRepository.getNutritionFactTable();
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<List<String>> getNourishmentNamesFromNutritionFactTable(){
        return nourishmentNamesFromNutritionFactTable;
    }

    public LiveData<List<NutritionFactTable>> getNutritionFactTable(){
        return nutritionFactTable;
    }

    public LiveData<String> getText() {
        return mText;
    }
}