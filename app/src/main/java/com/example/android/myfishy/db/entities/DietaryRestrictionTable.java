package com.example.android.myfishy.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dietary_restriction_table")
public class DietaryRestrictionTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int diet_plan_id;
    private String condition_name;
    private String diet_name;
    private String nourishment_category;
    private float table_salt;
    private float sodium;
    private float potassium;
    private float calcium;
    private float phosphor;
    private float protein;
    private float calories;
    private float liquid_intake;

    public DietaryRestrictionTable(String condition_name, String diet_name,
                                   String nourishment_category, float table_salt, float sodium, float potassium,
                                   float calcium, float phosphor, float protein, float calories, float liquid_intake) {
        this.condition_name = condition_name;
        this.diet_name = diet_name;
        this.nourishment_category = nourishment_category;
        this.table_salt = table_salt;
        this.sodium = sodium;
        this.potassium = potassium;
        this.calcium = calcium;
        this.phosphor = phosphor;
        this.protein = protein;
        this.calories = calories;
        this.liquid_intake = liquid_intake;
    }

    public int getDiet_plan_id() {
        return diet_plan_id;
    }

    public String getCondition_name() {
        return condition_name;
    }

    public String getDiet_name() {
        return diet_name;
    }

    public String getNourishment_category() {
        return nourishment_category;
    }

    public float getTable_salt() {
        return table_salt;
    }

    public float getSodium() {
        return sodium;
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

    public float getProtein() {
        return protein;
    }

    public float getCalories() {
        return calories;
    }

    public float getLiquid_intake() {
        return liquid_intake;
    }
}
