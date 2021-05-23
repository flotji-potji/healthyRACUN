package com.example.android.myfishy.db;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.android.myfishy.db.entities.*;
import com.example.android.myfishy.db.relations.MealConsistsOfNourishments;
import com.example.android.myfishy.db.relations.UserEatsMeals;

import java.util.List;

@Dao
public interface HealthyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDiet(Diet diet);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDietaryRestrictionTable(DietaryRestrictionTable dietaryRestrictionTable);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal meal);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNourishment(Nourishment nourishment);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNutritionFactTable(NutritionFactTable nutritionFactTable);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);

    @Transaction
    @Query("SELECT * FROM meal WHERE meal_id = :mealId")
    LiveData<List<MealConsistsOfNourishments>> getMealConsistsOfNourishments(int mealId);

    @Transaction
    @Query("SELECT * FROM user WHERE username = :username")
    LiveData<List<UserEatsMeals>> getUserEatMeals(String username);

    @Transaction
    @Query("SELECT * FROM user WHERE username = :username")
    LiveData<List<UserEatsMeals>> getUserGotDiets(String username);

    @Query("SELECT * FROM dietary_restriction_table")
    LiveData<List<DietaryRestrictionTable>> getDietaryRestrictionTable();

    @Query("SELECT * FROM nutrition_fact_table")
    LiveData<List<NutritionFactTable>> getNutritionFactTable();

    @Query("SELECT * FROM user WHERE username = :username")
    LiveData<User> getUser(String username);

    @Query("SELECT nourishment_name FROM nutrition_fact_table")
    LiveData<List<String>> getNourishmentNamesFromNutritionFactTable();
}
