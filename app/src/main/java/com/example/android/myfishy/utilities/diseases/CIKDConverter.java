package com.example.android.myfishy.utilities.diseases;

import com.example.android.myfishy.db.entities.NutritionFactTable;
import com.example.android.myfishy.db.entities.User;
import com.example.android.myfishy.repo.HealthyRepository;

/**
 * Chronic Inflammatory Kidney Disease
 */
public class CIKDConverter {

    private NutritionFactTable nFT;
    private User user;

    public CIKDConverter(NutritionFactTable nutritionFactTable, User user) {
        this.nFT = nutritionFactTable;
        this.user = user;
    }

    private float getNaCl() {
        return nFT.getTable_salt();
    }

    private float convertSodium() {
        //convert mg (excel) into mmol (wanted)
        return nFT.getSodium() / HealthyRepository.MOLAR_MASS_SODIUM;
    }

    private float convertWater() {
        return nFT.getH20();
    }

    private float convertPotassium() {
        return nFT.getPotassium();
    }

    private float convertCalcium() {
        //  mg into g
        return nFT.getCalcium() / 1000;
    }

    private float convertCalories() {
        //kcal into kcal/kg
        return nFT.getCalories() / user.getWeight();
    }

    private float convertProtein() {
        //  g into g/kg
        return nFT.getProtein() / user.getWeight();
    }
}
