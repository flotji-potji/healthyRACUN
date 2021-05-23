package com.example.android.myfishy.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;
import com.example.android.myfishy.db.entities.Diet;
import com.example.android.myfishy.db.entities.User;

import java.util.List;

public class UserHasDiets {

    @Embedded
    public User user;

    @Relation(parentColumn = "username", entityColumn = "username")
    public List<Diet> diets;

    public User getUser() {
        return user;
    }

    public List<Diet> getDiets() {
        return diets;
    }
}
