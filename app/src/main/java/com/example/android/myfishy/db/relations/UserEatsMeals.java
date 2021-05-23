package com.example.android.myfishy.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;
import com.example.android.myfishy.db.entities.Meal;
import com.example.android.myfishy.db.entities.User;

import java.util.List;

public class UserEatsMeals {

    @Embedded
    public User user;

    @Relation(parentColumn = "username", entityColumn = "username")
    public List<Meal> meals;

    public User getUser() {
        return user;
    }

    public List<Meal> getMeals() {
        return meals;
    }
}
