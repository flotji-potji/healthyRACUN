package com.example.android.myfishy.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;
import com.example.android.myfishy.db.entities.Meal;
import com.example.android.myfishy.db.entities.Nourishment;

import java.util.List;

public class MealConsistsOfNourishments {

    @Embedded
    public Meal meal;

    @Relation(parentColumn = "meal_id", entityColumn = "meal_id")
    public List<Nourishment> nourishments;

}
