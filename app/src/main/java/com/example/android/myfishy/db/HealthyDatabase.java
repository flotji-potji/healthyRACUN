package com.example.android.myfishy.db;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.android.myfishy.db.entities.*;

@Database(
        entities = {
                Diet.class, DietaryRestrictionTable.class, Meal.class,
                Nourishment.class, NutritionFactTable.class, User.class
        },
        version = 1,
        exportSchema = false)
public abstract class HealthyDatabase extends androidx.room.RoomDatabase {

    public abstract HealthyDao healthyDao();

    private static HealthyDatabase INSTANCE;

    public static HealthyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HealthyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HealthyDatabase.class,
                            "healthy_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
