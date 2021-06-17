package com.example.android.myfishy.ui.create_meal;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.example.android.myfishy.db.entities.Meal;
import com.example.android.myfishy.db.entities.Nourishment;
import com.example.android.myfishy.db.entities.NutritionFactTable;
import com.example.android.myfishy.repo.HealthyRepository;

public class CreateMealViewModel extends AndroidViewModel {

    public static final String CREATE_MEAL_VIEW_MODEL_TAG =
            HealthyRepository.buildTag(HealthyRepository.CREATE_MEAL_TAG,
                    HealthyRepository.VIEW_MODEL_TAG);

    private HealthyRepository healthyRepository;
    private Application root;

    public CreateMealViewModel(@NonNull Application application) {
        super(application);
        root = application;
        healthyRepository = new HealthyRepository(application, CREATE_MEAL_VIEW_MODEL_TAG);

    }

    public void insertNourishment(int mealId, Nourishment nourishment){
        nourishment.setMeal_id(mealId);
        healthyRepository.insertNourishment(nourishment);
    }

    public void insertMeal(Meal meal){
        healthyRepository.insertMeal(meal);
    }

    public LiveData<NutritionFactTable> getNutritionById(final int id){
        return healthyRepository.getNutritionById(id);
    }

    public LiveData<Integer> getLastSavedMealId() {
        return healthyRepository.getLastSavedMealId();
    }
}
