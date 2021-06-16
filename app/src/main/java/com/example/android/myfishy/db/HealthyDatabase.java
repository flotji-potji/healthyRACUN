package com.example.android.myfishy.db;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.room.migration.Migration;
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
        version = 3
)
public abstract class HealthyDatabase extends androidx.room.RoomDatabase {

    public abstract HealthyDao healthyDao();

    private static ExtractCSV ex;

    private static HealthyDatabase INSTANCE;

    private static RoomDatabase.Callback roomDatabaseCallback =
            new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    populateDb(INSTANCE);
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

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE meal RENAME TO _meal_old");
            database.execSQL("CREATE TABLE meal (" +
                    "meal_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "meal_type INTEGER NOT NULL," +
                    "username TEXT," +
                    "meal_name TEXT," +
                    "date_added INTEGER NOT NULL)");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE nourishment RENAME TO _nourishment_old");
            database.execSQL("CREATE TABLE nourishment (" +
                    "nourishment_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "meal_id INTEGER NOT NULL," +
                    "nourishment_category TEXT," +
                    "nourishment_name TEXT," +
                    "nourishment_synonym TEXT," +
                    "calories FLOAT NOT NULL," +
                    "fat FLOAT NOT NULL," +
                    "saturated_fatty_acids FLOAT NOT NULL," +
                    "unsaturated_fatty_acids FLOAT NOT NULL," +
                    "carbohydrates_all FLOAT NOT NULL," +
                    "simple_sugars FLOAT NOT NULL," +
                    "etoh FLOAT NOT NULL," +
                    "h20 FLOAT NOT NULL," +
                    "table_salt FLOAT NOT NULL," +
                    "sodium FLOAT NOT NULL," +
                    "chlorine FLOAT NOT NULL," +
                    "magnesium FLOAT NOT NULL," +
                    "potassium FLOAT NOT NULL," +
                    "calcium FLOAT NOT NULL," +
                    "phosphor FLOAT NOT NULL," +
                    "iron FLOAT NOT NULL," +
                    "protein FLOAT NOT NULL)");
        }
    };

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
                            .addCallback(roomDatabaseCallback)
                            .addMigrations(MIGRATION_1_2)
                            .addMigrations(MIGRATION_2_3)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
