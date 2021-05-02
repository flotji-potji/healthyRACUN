package com.example.android.myfishy.utilities;

import android.util.Log;
import android.util.Pair;
import com.example.android.myfishy.db.entities.NutritionFactTable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExtractCSV {

    private BufferedReader br;

    private final String EXTRACT_CSV_TAG = "EXTRACT_CSV";

    private final short NOURISHMENT_NAME_COLUMN = 3;
    private final short NOURISHMENT_SYNONYM_COLUMN = 4;
    private final short NOURISHMENT_CATEGORY_COLUMN = 5;
    private final short CALORIES_COLUMN = 8;
    private final short FAT_COLUMN = 14;
    private final short SATURATED_FATTY_ACIDS_COLUMN = 17;
    private final short UNSATURATED_FATTY_ACIDS_COLUMN = 23;
    private final short CARBOHYDRATES_ALL_COLUMN = 29;
    private final short SIMPLE_SUGARS_COLUMN = 32;
    private final short ETOH_COLUMN = 47;
    private final short H2O_COLUMN = 50;
    private final short TABLE_SALT_COLUMN = 44;
    private final short SODIUM_COLUMN = 101;
    private final short CHLORINE_COLUMN = 104;
    private final short MAGNESIUM_COLUMN = 110;
    private final short POTASSIUM_COLUMN = 98;
    private final short CALCIUM_COLUMN = 107;
    private final short PHOSPHOR_COLUMN = 113;
    private final short IRON_COLUMN = 116;
    private final short PROTEIN_COLUMN = 41;

    public ExtractCSV(InputStream is) throws IOException {
        br = new BufferedReader(new InputStreamReader(is));
    }

    public List<String> next() throws IOException {
        char[] charLine = br.readLine().toCharArray();
        List<String> res = new ArrayList<>();
        CharState state = CharState.DELIMITER;
        String cellContext = "";
        Pair<CharState, String> ret;
        for (int i = 0; i < charLine.length; i++) {
            ret = state.handleChar(charLine, i, cellContext);
            state = ret.first;
            cellContext = ret.second;
            if (state == CharState.DELIMITER) {
                res.add(cellContext);
                cellContext = "";
            }
        }
        res.add(cellContext);
        return res;
    }

    public NutritionFactTable getNutritionFactTableRow(List<String> line) {
        String nourishment_category = !line.get(NOURISHMENT_CATEGORY_COLUMN).isEmpty() ? line.get(NOURISHMENT_CATEGORY_COLUMN) : null;
        String nourishment_name = !line.get(NOURISHMENT_NAME_COLUMN).isEmpty() ? line.get(NOURISHMENT_NAME_COLUMN) : null;
        String nourishment_synonym = !line.get(NOURISHMENT_SYNONYM_COLUMN).isEmpty() ? line.get(NOURISHMENT_SYNONYM_COLUMN) : null;
        float calories;
        try {
            calories = Float.parseFloat(line.get(CALORIES_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            calories = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float fat;
        try {
            fat = Float.parseFloat(line.get(FAT_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            fat = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float saturated_fatty_acids;
        try {
            saturated_fatty_acids = Float.parseFloat(line.get(SATURATED_FATTY_ACIDS_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            saturated_fatty_acids = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float unsaturated_fatty_acids;
        try {
            unsaturated_fatty_acids = Float.parseFloat(line.get(UNSATURATED_FATTY_ACIDS_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            unsaturated_fatty_acids = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float carbohydrates_all;
        try {
            carbohydrates_all = Float.parseFloat(line.get(CARBOHYDRATES_ALL_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            carbohydrates_all = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float simple_sugars;
        try {
            simple_sugars = Float.parseFloat(line.get(SIMPLE_SUGARS_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            simple_sugars = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float etoh;
        try {
            etoh = Float.parseFloat(line.get(ETOH_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            etoh = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float h2o;
        try {
            h2o = Float.parseFloat(line.get(H2O_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            h2o = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float table_salt;
        try {
            table_salt = Float.parseFloat(line.get(TABLE_SALT_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            table_salt = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float sodium = 0;
        try {
            sodium = Float.parseFloat(line.get(SODIUM_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            sodium = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float chlorine;
        try {
            chlorine = Float.parseFloat(line.get(CHLORINE_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            chlorine = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float magnesium;
        try {
            magnesium = Float.parseFloat(line.get(MAGNESIUM_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            magnesium = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float potassium;
        try {
            potassium = Float.parseFloat(line.get(POTASSIUM_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            potassium = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float calcium;
        try {
            calcium = Float.parseFloat(line.get(CALCIUM_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            calcium = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float phosphor;
        try {
            phosphor = Float.parseFloat(line.get(PHOSPHOR_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            phosphor = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float iron;
        try {
            iron = Float.parseFloat(line.get(CALORIES_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            iron = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }
        float protein;
        try {
            protein = Float.parseFloat(line.get(PROTEIN_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            protein = -1;
            Log.e(EXTRACT_CSV_TAG, e.getMessage());
        }

        return new NutritionFactTable(
                nourishment_category, nourishment_name, nourishment_synonym, calories,
                fat, saturated_fatty_acids, unsaturated_fatty_acids, carbohydrates_all, simple_sugars, etoh, h2o,
                table_salt, sodium, chlorine, magnesium, potassium, calcium, phosphor, iron, protein);
    }

}
