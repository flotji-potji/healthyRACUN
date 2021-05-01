package com.example.android.myfishy.db;

import androidx.room.Dao;
import androidx.room.Insert;
import com.example.android.myfishy.db.entities.*;

@Dao
public interface HealthyDao {

    @Insert
    void insertDiet(Diet diet);

    @Insert
    void insertDietaryRestrictionTable(DietaryRestrictionTable dietaryRestrictionTable);

    @Insert
    void insertMeal(Meal meal);

    @Insert
    void insertNourishment(Nourishment nourishment);

    @Insert
    void insertNutritionFactTable(NutritionFactTable nutritionFactTable);

    @Insert
    void insertUser(User user);



}
