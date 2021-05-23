package com.example.android.myfishy.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal")
public class Meal {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int meal_id;
    private String username;
    private String meal_name;
    private int meal_type;
    private long date_added;

    public Meal(@NonNull int meal_id, String username, String meal_name, int meal_type, long date_added) {
        this.meal_id = meal_id;
        this.username = username;
        this.meal_name = meal_name;
        this.meal_type = meal_type;
        this.date_added = date_added;
    }

    public int getMeal_id() {
        return meal_id;
    }

    public String getUsername() {
        return username;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public int getMeal_type() {
        return meal_type;
    }

    public long getDate_added() {
        return date_added;
    }
}
