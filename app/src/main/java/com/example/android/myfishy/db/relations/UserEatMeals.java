package com.example.android.myfishy.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;
import com.example.android.myfishy.db.entities.Meal;
import com.example.android.myfishy.db.entities.User;

import java.util.List;

public class UserEatMeals {

    @Embedded
    public User user;

    @Relation(parentColumn = "username", entityColumn = "username")
    public List<Meal> meals;

}
