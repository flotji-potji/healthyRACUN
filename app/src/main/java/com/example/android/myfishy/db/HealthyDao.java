package com.example.android.myfishy.db;

import androidx.room.*;
import com.example.android.myfishy.db.entities.*;
import com.example.android.myfishy.db.relations.MealConsistsOfNourishments;
import com.example.android.myfishy.db.relations.UserEatMeals;
import com.example.android.myfishy.db.relations.UserGotDiets;

import java.util.List;

@Dao
public interface HealthyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDiet(Diet diet);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDietaryRestrictionTable(DietaryRestrictionTable dietaryRestrictionTable);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal meal);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNourishment(Nourishment nourishment);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNutritionFactTable(NutritionFactTable nutritionFactTable);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);

    @Transaction
    @Query("SELECT * FROM meal WHERE meal_id = :mealId")
    List<MealConsistsOfNourishments> getMealConsistsOfNourishments(int mealId);

    @Transaction
    @Query("SELECT * FROM user WHERE username = :username")
    List<UserEatMeals> getUserEatMeals(String username);

    @Transaction
    @Query("SELECT * FROM user WHERE username = :username")
    List<UserGotDiets> getUserGotDiets(String username);
}
