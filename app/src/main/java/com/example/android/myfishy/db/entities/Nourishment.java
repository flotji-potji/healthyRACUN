package com.example.android.myfishy.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "nourishment")
public class Nourishment {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int nourishment_id;
    private int meal_id;
    private String nourishment_category;
    private String nourishment_name;
    private String nourishment_synonym;
    private float calories;
    private float fat;
    private float saturated_fatty_acids;
    private float unsaturated_fatty_acids;
    private float carbohydrates_all;
    private float simple_sugars;
    private float etoh;
    private float h20;
    private float table_salt;
    private float sodium;
    private float chlorine;
    private float magnesium;
    private float potassium;
    private float calcium;
    private float phosphor;
    private float iron;
    private float protein;
    private float fibers;

    /**
     * @param nourishment_id
     * @param meal_id
     * @param nourishment_category
     * @param nourishment_name
     * @param nourishment_synonym
     * @param calories
     * @param fat
     * @param saturated_fatty_acids
     * @param unsaturated_fatty_acids
     * @param carbohydrates_all
     * @param simple_sugars
     * @param etoh
     * @param h20
     * @param table_salt
     * @param sodium
     * @param chlorine
     * @param magnesium
     * @param potassium
     * @param calcium
     * @param phosphor
     * @param iron
     * @param protein
     */
    public Nourishment(int meal_id, String nourishment_category, String nourishment_name,
                       String nourishment_synonym, float calories, float fat, float saturated_fatty_acids,
                       float unsaturated_fatty_acids, float carbohydrates_all, float simple_sugars, float etoh,
                       float h20, float table_salt, float sodium, float chlorine, float magnesium, float potassium,
                       float calcium, float phosphor, float iron, float protein, float fibers) {
        this.meal_id = meal_id;
        this.nourishment_category = nourishment_category;
        this.nourishment_name = nourishment_name;
        this.nourishment_synonym = nourishment_synonym;
        this.calories = calories;
        this.fat = fat;
        this.saturated_fatty_acids = saturated_fatty_acids;
        this.unsaturated_fatty_acids = unsaturated_fatty_acids;
        this.carbohydrates_all = carbohydrates_all;
        this.simple_sugars = simple_sugars;
        this.etoh = etoh;
        this.h20 = h20;
        this.table_salt = table_salt;
        this.sodium = sodium;
        this.chlorine = chlorine;
        this.magnesium = magnesium;
        this.potassium = potassium;
        this.calcium = calcium;
        this.phosphor = phosphor;
        this.iron = iron;
        this.protein = protein;
        this.fibers = fibers;
    }

    public void setNourishment_id(int nourishment_id) {
        this.nourishment_id = nourishment_id;
    }

    public int getNourishment_id() {
        return nourishment_id;
    }

    public void setMeal_id(int meal_id){
        this.meal_id = meal_id;
    }

    public int getMeal_id() {
        return meal_id;
    }

    public String getNourishment_category() {
        return nourishment_category;
    }

    public String getNourishment_name() {
        return nourishment_name;
    }

    public String getNourishment_synonym() {
        return nourishment_synonym;
    }

    public float getCalories() {
        return calories;
    }

    public float getFat() {
        return fat;
    }

    public float getSaturated_fatty_acids() {
        return saturated_fatty_acids;
    }

    public float getUnsaturated_fatty_acids() {
        return unsaturated_fatty_acids;
    }

    public float getCarbohydrates_all() {
        return carbohydrates_all;
    }

    public float getSimple_sugars() {
        return simple_sugars;
    }

    public float getEtoh() {
        return etoh;
    }

    public float getH20() {
        return h20;
    }

    public float getTable_salt() {
        return table_salt;
    }

    public float getSodium() {
        return sodium;
    }

    public float getChlorine() {
        return chlorine;
    }

    public float getMagnesium() {
        return magnesium;
    }

    public float getPotassium() {
        return potassium;
    }

    public float getCalcium() {
        return calcium;
    }

    public float getPhosphor() {
        return phosphor;
    }

    public float getIron() {
        return iron;
    }

    public float getProtein() {
        return protein;
    }

    public float getFibers() {
        return fibers;
    }
}
