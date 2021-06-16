package com.example.android.myfishy.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "diet")
public class Diet {

    @PrimaryKey
    @NonNull
    private int diet_id;
    private String username;
    private String condition_name;
    private String diet_name;
    private float table_salt;
    private float sodium;
    private float potassium;
    private float calcium;
    private float phosphor;
    private float protein;
    private float calories;
    private float liquid_intake;
    private float carbs;
    private float fats;
    private float fibers;

    public Diet(@NonNull int diet_id, String username, String condition_name, String diet_name,
                float table_salt, float sodium, float potassium,
                float calcium, float phosphor, float protein, float calories, float liquid_intake,
                float carbs, float fats, float fibers) {
        this.username = username;
        this.diet_id = diet_id;
        this.condition_name = condition_name;
        this.diet_name = diet_name;
        this.table_salt = table_salt;
        this.sodium = sodium;
        this.potassium = potassium;
        this.calcium = calcium;
        this.phosphor = phosphor;
        this.protein = protein;
        this.calories = calories;
        this.liquid_intake = liquid_intake;
        this.carbs = carbs;
        this.fats = fats;
        this.fibers = fibers;
    }

    public int getDiet_id() {
        return diet_id;
    }

    public String getUsername() {
        return username;
    }

    public String getCondition_name() {
        return condition_name;
    }

    public String getDiet_name() {
        return diet_name;
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

    public float getCarbs() {
        return carbs;
    }

    public float getFats() {
        return fats;
    }

    public float getFibers() {
        return fibers;
    }
}
