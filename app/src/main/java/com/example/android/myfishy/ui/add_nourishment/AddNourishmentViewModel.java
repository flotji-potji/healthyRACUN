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

    public boolean isNutritionLiquid(NutritionFactTable nutritionFactTable) {
        return nutritionFactTable
                .getNourishment_category()
                .contains("Getränk") ||
                nutritionFactTable
                        .getNourishment_category()
                        .contains("Öl");
    }

    public NutritionFactTable calculateEnteredNutritionQuantity(float quantity, NutritionFactTable ntf) {
        float factor = quantity / HealthyRepository.NUTRITION_QUANTITY_PER_SERVING_MG;
        return new NutritionFactTable(
                ntf.getNourishment_category(),
                ntf.getNourishment_name(),
                ntf.getNourishment_synonym(),
                ntf.getCalories() * factor,
                ntf.getFat() * factor,
                ntf.getSaturated_fatty_acids() * factor,
                ntf.getUnsaturated_fatty_acids() * factor,
                ntf.getCarbohydrates_all() * factor,
                ntf.getSimple_sugars() * factor,
                ntf.getEtoh() * factor,
                ntf.getH20() * factor,
                ntf.getTable_salt() * factor,
                ntf.getSodium() * factor,
                ntf.getChlorine() * factor,
                ntf.getMagnesium() * factor,
                ntf.getPotassium() * factor,
                ntf.getCalcium() * factor,
                ntf.getPhosphor() * factor,
                ntf.getIron() * factor,
                ntf.getProtein() * factor
        );
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

}
