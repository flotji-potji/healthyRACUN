package com.example.android.myfishy.db;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.android.myfishy.R;
import com.example.android.myfishy.db.entities.*;
import com.example.android.myfishy.utilities.ExtractCSV;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Database(
        entities = {
                Diet.class, DietaryRestrictionTable.class, Meal.class,
                Nourishment.class, NutritionFactTable.class, User.class
        },
        version = 1)
public abstract class HealthyDatabase extends androidx.room.RoomDatabase {

    public abstract HealthyDao healthyDao();

    private static ExtractCSV ex;

    private static HealthyDatabase INSTANCE;

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                }
            };

    private static void populateDb(HealthyDatabase db) {
        final HealthyDao mDao = db.healthyDao();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    populateNutritionFactTable(mDao);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void populateNutritionFactTable(HealthyDao healthyDao) throws IOException {
        List<String> csvRow = ex.next();
        int currLine = 0;
        while (!csvRow.isEmpty()) {
            currLine++;
            if (currLine > 3)
                healthyDao.insertNutritionFactTable(ex.getNutritionFactTableRow(csvRow));
            csvRow = ex.next();
        }
    }

    public static HealthyDatabase getDatabase(final Context context) throws IOException {
        ex = new ExtractCSV(context);
        if (INSTANCE == null) {
            synchronized (HealthyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HealthyDatabase.class,
                            "healthy_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
