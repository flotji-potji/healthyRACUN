package com.example.android.myfishy.db;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.android.myfishy.R;
import com.example.android.myfishy.db.entities.*;
import com.example.android.myfishy.utilities.ExtractCSV;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Database(
        entities = {
                Diet.class, DietaryRestrictionTable.class, Meal.class,
                Nourishment.class, NutritionFactTable.class, User.class
        },
        version = 4
)
public abstract class HealthyDatabase extends androidx.room.RoomDatabase {

    public abstract HealthyDao healthyDao();

    private static ExtractCSV exNutri;
    private static ExtractCSV exDiet;

    private static HealthyDatabase INSTANCE;

    private static RoomDatabase.Callback roomDatabaseNutriCallback =
            new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    populateDb(INSTANCE);
                }
            };

    private static RoomDatabase.Callback roomDatabaseDietCallback =
            new Callback() {
                @Override
                public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    populateDbDiet(INSTANCE);
                }
            };

    private static void populateDb(HealthyDatabase db) {
        final HealthyDao mDao = db.healthyDao();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    populateNutritionFactTable(mDao);
                    exNutri.closeBr();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void populateDbDiet(HealthyDatabase db) {
        final HealthyDao mDao = db.healthyDao();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    populateDietaryRestrictionTable(mDao);
                    exDiet.closeBr();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void populateNutritionFactTable(HealthyDao healthyDao) throws IOException {
        List<String> csvRow = exNutri.next();
        int currLine = 0;
        while (!csvRow.isEmpty()) {
            currLine++;
            if (currLine > 3)
                healthyDao.insertNutritionFactTable(exNutri.getNutritionFactTableRow(csvRow));
            csvRow = exNutri.next();
        }
    }

    private static void populateDietaryRestrictionTable(HealthyDao healthyDao) throws IOException {
        List<String> csvRow = exDiet.next();
        int currLine = 0;
        while (!csvRow.isEmpty()) {
            currLine++;
            if (currLine > 12)
                healthyDao.insertDietaryRestrictionTable(
                        exDiet.getDietaryRestrictionTableRow(csvRow));
            csvRow = exDiet.next();
        }
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

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE nutrition_fact_table RENAME TO _nutrition_fact_table_old");
            database.execSQL("ALTER TABLE nourishment RENAME TO _nourishment_old_2");
            database.execSQL("ALTER TABLE diet RENAME TO _diet_old");
            database.execSQL("ALTER TABLE dietary_restriction_table RENAME TO _dietary_restriction_table_old");

            database.execSQL("CREATE TABLE nutrition_fact_table (" +
                    "nutrition_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "nourishment_category TEXT," +
                    "nourishment_name TEXT," +
                    "nourishment_synonym TEXT," +
                    "calories REAL NOT NULL," +
                    "fat REAL NOT NULL," +
                    "saturated_fatty_acids REAL NOT NULL," +
                    "unsaturated_fatty_acids REAL NOT NULL," +
                    "carbohydrates_all REAL NOT NULL," +
                    "simple_sugars REAL NOT NULL," +
                    "etoh REAL NOT NULL," +
                    "h20 REAL NOT NULL," +
                    "table_salt REAL NOT NULL," +
                    "sodium REAL NOT NULL," +
                    "chlorine REAL NOT NULL," +
                    "magnesium REAL NOT NULL," +
                    "potassium REAL NOT NULL," +
                    "calcium REAL NOT NULL," +
                    "phosphor REAL NOT NULL," +
                    "iron REAL NOT NULL," +
                    "protein REAL NOT NULL," +
                    "fibers REAL NOT NULL)");

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
                    "protein FLOAT NOT NULL," +
                    "fibers FLOAT NOT NULL)");

            database.execSQL("CREATE TABLE dietary_restriction_table (" +
                    "diet_plan_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "condition_name TEXT," +
                    "diet_name TEXT," +
                    "table_salt FLOAT NOT NULL," +
                    "sodium FLOAT NOT NULL," +
                    "potassium FLOAT NOT NULL," +
                    "calcium FLOAT NOT NULL," +
                    "phosphor FLOAT NOT NULL," +
                    "protein REAL NOT NULL," +
                    "calories FLOAT NOT NULL," +
                    "liquid_intake FLOAT NOT NULL," +
                    "carbs FLOAT NOT NULL," +
                    "fats FLOAT NOT NULL," +
                    "fibers FLOAT NOT NULL)");

            database.execSQL("CREATE TABLE diet (" +
                    "diet_id INTEGER PRIMARY KEY NOT NULL," +
                    "username TEXT," +
                    "condition_name TEXT," +
                    "diet_name TEXT," +
                    "table_salt REAL NOT NULL," +
                    "sodium REAL NOT NULL," +
                    "potassium REAL NOT NULL," +
                    "calcium REAL NOT NULL," +
                    "phosphor REAL NOT NULL," +
                    "protein REAL NOT NULL," +
                    "calories REAL NOT NULL," +
                    "liquid_intake REAL NOT NULL," +
                    "carbs REAL NOT NULL," +
                    "fats REAL NOT NULL," +
                    "fibers REAL NOT NULL)");
        }
    };

    public static HealthyDatabase getDatabase(final Context context) throws IOException {
        exNutri =
                new ExtractCSV(new InputStreamReader(context.getResources().openRawResource(R.raw.nutrition_table)));
        exDiet =
                new ExtractCSV(new InputStreamReader(context.getResources().openRawResource(R.raw.dietary_restriction_table)));
        if (INSTANCE == null) {
            synchronized (HealthyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HealthyDatabase.class,
                            "healthy_database")
                            .addCallback(roomDatabaseNutriCallback)
                            .addCallback(roomDatabaseDietCallback)
                            .addMigrations(MIGRATION_1_2)
                            .addMigrations(MIGRATION_2_3)
                            .addMigrations(MIGRATION_3_4)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
