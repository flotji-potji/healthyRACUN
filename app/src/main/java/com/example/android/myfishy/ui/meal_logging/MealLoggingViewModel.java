package com.example.android.myfishy.ui.meal_logging;

import android.app.Application;
import android.content.res.TypedArray;
import androidx.annotation.NonNull;
import androidx.lifecycle.*;
import com.example.android.myfishy.MainActivity;
import com.example.android.myfishy.R;
import com.example.android.myfishy.db.entities.Meal;
import com.example.android.myfishy.db.relations.UserEatsMeals;
import com.example.android.myfishy.repo.HealthyRepository;

import java.util.ArrayList;
import java.util.List;

public class MealLoggingViewModel extends AndroidViewModel {

    public static final String MEAL_LOGGING_VIEW_MODEL_TAG =
            HealthyRepository.buildTag(HealthyRepository.MEAL_LOGGING_TAG, HealthyRepository.VIEW_MODEL_TAG);

    private HealthyRepository healthyRepository;
    private LiveData<List<UserEatsMeals>> userEatMeals;
    private TypedArray mealTitleListStringIds;
    private TypedArray mealTitleListViewIds;
    private TypedArray mealSubTitleListStringIds;
    private TypedArray mealSubTitleListViewIds;
    private TypedArray breakfastMealListViewIds;
    private TypedArray lunchMealListViewIds;
    private TypedArray dinnerMealListViewIds;

    public MealLoggingViewModel(@NonNull Application application) {
        // initiating fragment section
        super(application);
        healthyRepository = new HealthyRepository(application, MEAL_LOGGING_VIEW_MODEL_TAG);

        // initiating live data section
        userEatMeals = healthyRepository.getUserJoinsMeal();
        mealTitleListStringIds =
                application.getResources().obtainTypedArray(R.array.fragment_meal_logging_meal_title_strings);
        mealTitleListViewIds =
                application.getResources().obtainTypedArray(R.array.fragment_meal_logging_meal_title_ids);
        mealSubTitleListStringIds =
                application.getResources().obtainTypedArray(R.array.fragment_meal_logging_meal_subtitle_strings);
        mealSubTitleListViewIds =
                application.getResources().obtainTypedArray(R.array.fragment_meal_logging_meal_subtitle_ids);
        breakfastMealListViewIds =
                application.getResources().obtainTypedArray(R.array.fragment_meal_logging_breakfast_meal_ids);
        lunchMealListViewIds =
                application.getResources().obtainTypedArray(R.array.fragment_meal_logging_lunch_meal_ids);
        dinnerMealListViewIds =
                application.getResources().obtainTypedArray(R.array.fragment_meal_logging_dinner_meal_ids);
    }

    public TypedArray getMealTitleListStringIds() {
        return mealTitleListStringIds;
    }

    public TypedArray getMealTitleListViewIds() {
        return mealTitleListViewIds;
    }

    public TypedArray getMealSubTitleListStringIds() {
        return mealSubTitleListStringIds;
    }

    public TypedArray getMealSubTitleListViewIds() {
        return mealSubTitleListViewIds;
    }

    public TypedArray getBreakfastMealListViewIds() {
        return breakfastMealListViewIds;
    }

    public TypedArray getLunchMealListViewIds() {
        return lunchMealListViewIds;
    }

    public TypedArray getDinnerMealListViewIds() {
        return dinnerMealListViewIds;
    }

    public LiveData<List<UserEatsMeals>> getUserMeals() {
        return userEatMeals;
    }

    public List<Meal> getUserBreakfastMeals(List<UserEatsMeals> userMealTable) {
        return getMealType(userMealTable, HealthyRepository.MEAL_TYPE_BREAKFAST);
    }

    public List<Meal> getUserLunchMeals(List<UserEatsMeals> userMealTable) {
        return getMealType(userMealTable, HealthyRepository.MEAL_TYPE_LUNCH);
    }

    public List<Meal> getUserDinnerMeals(List<UserEatsMeals> userMealTable) {
        return getMealType(userMealTable, HealthyRepository.MEAL_TYPE_DINNER);
    }

    // ---- private class helper methods ---- //
    private List<Meal> getMealType(List<UserEatsMeals> userMealTable, int mealTypeID) {
        List<Meal> res = new ArrayList<>();
        if (userMealTable != null) {
            for (UserEatsMeals joinedUserMealTable : userMealTable) {
                if (joinedUserMealTable.getUser().getUsername().equals(MainActivity.getCurrUser())) {
                    for (Meal meal : joinedUserMealTable.getMeals()) {
                        if (meal.getMeal_type() == mealTypeID) {
                            res.add(meal);
                        }
                    }
                }
            }
        }
        return res;
    }
}