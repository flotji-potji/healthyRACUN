package com.example.android.myfishy.utilities.diseases;

import com.example.android.myfishy.db.entities.Diet;
import com.example.android.myfishy.db.entities.Nourishment;
import com.example.android.myfishy.db.entities.User;
import com.example.android.myfishy.repo.HealthyRepository;

import java.util.List;

/**
 * Chronic Inflammatory Kidney Disease
 */
public class CIKDConverter extends Disease {

    private float table_salt;
    private float sodium;
    private float potassium;
    private float calcium;
    private float phosphor;
    private float protein;
    private float calories;
    private float liquid_intake;

    public CIKDConverter(List<Nourishment> nourishmentList, User user) {
        super(nourishmentList, user);
        this.table_salt = 0;
        this.sodium = 0;
        this.potassium = 0;
        this.calcium = 0;
        this.phosphor = 0;
        this.protein = 0;
        this.calories = 0;
        this.liquid_intake = 0;
    }

    private void addNaCl() {
        table_salt += nourishment.getTable_salt();
    }

    private void convertAndAddSodium() {
        //convert mg (excel) into mmol (wanted)
        sodium += nourishment.getSodium() / HealthyRepository.MOLAR_MASS_SODIUM;
    }

    private void convertAndAddWater() {
        liquid_intake += nourishment.getH20();
    }

    private void convertAndAddPotassium() {
        potassium += nourishment.getPotassium();
    }

    private void convertAndAddCalcium() {
        //  mg into g
        calcium += nourishment.getCalcium() / 1000;
    }

    private void convertAndAddCalories() {
        //kcal into kcal/kg
        calories += nourishment.getCalories() / user.getWeight();
    }

    private void convertAndAddProtein() {
        //  g into g/kg
        protein += nourishment.getProtein() / user.getWeight();
    }


    @Override
    public Diet evaluateDietaryAppliance() {
        for (Nourishment item : nList) {
            nourishment = item;
            addNaCl();
            convertAndAddSodium();
            convertAndAddWater();
            convertAndAddPotassium();
            convertAndAddCalcium();
            convertAndAddCalories();
            convertAndAddProtein();
        }
        return new Diet(
                1,
                "",
                "",
                "",
                table_salt,
                sodium,
                potassium,
                calcium,
                0,
                protein,
                calories,
                liquid_intake,
                0,
                0,
                0
        );
    }

}
