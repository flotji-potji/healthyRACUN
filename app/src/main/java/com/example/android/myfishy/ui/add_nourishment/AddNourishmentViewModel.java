package com.example.android.myfishy.ui.add_nourishment;

import android.app.Application;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.android.myfishy.db.entities.NutritionFactTable;
import com.example.android.myfishy.db.relations.UserHasDiets;
import com.example.android.myfishy.repo.HealthyRepository;

import java.util.List;

public class AddNourishmentViewModel extends AndroidViewModel {

    public static final String ADD_NOURISHMENT_VIEW_MODEL_TAG =
            HealthyRepository.buildTag(HealthyRepository.ADD_NOURISHMENT_TAG, HealthyRepository.VIEW_MODEL_TAG);

    private HealthyRepository healthyRepository;
    private LiveData<List<NutritionFactTable>> nutritionFactTable;
    private LiveData<List<String>> nourishmentNamesFromNutritionFactTable;
    private LiveData<List<UserHasDiets>> userHasDiets;

    public AddNourishmentViewModel(@NonNull Application application) {
        super(application);
        healthyRepository = new HealthyRepository(application, ADD_NOURISHMENT_VIEW_MODEL_TAG);

        nutritionFactTable = healthyRepository.getNutritionFactTable();
        nourishmentNamesFromNutritionFactTable = healthyRepository.getNourishmentNamesFromNutritionFactTable();
        userHasDiets = healthyRepository.getUserJoinsDiet();
    }

    public LiveData<List<NutritionFactTable>> getNutritionFactTable() {
        return nutritionFactTable;
    }

    public LiveData<List<String>> getNourishmentNamesFromNutritionFactTable() {
        return nourishmentNamesFromNutritionFactTable;
    }

    public LiveData<List<UserHasDiets>> getUserHasDiets() {
        return userHasDiets;
    }

    public boolean checkDietaryRestriction(
            NutritionFactTable nutritionFactTable) {
        // TODO: CHECK IF DIETARY RESTRICTION APPLIES
        return true;
    }

    public NutritionFactTable getMatchingNutritionFactTable(
            List<NutritionFactTable> nutritionFacts,
            String match) {
        for (NutritionFactTable item : nutritionFacts) {
            if (item.getNourishment_name().equals(match)) {
                return item;
            }
        }
        return null;
    }
}
