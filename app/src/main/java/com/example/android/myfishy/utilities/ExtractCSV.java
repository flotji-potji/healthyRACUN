package com.example.android.myfishy.utilities;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.example.android.myfishy.R;
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

    public ExtractCSV(Context context) throws IOException {
        br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.nutrition_table)));
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
            if (line.get(CALORIES_COLUMN).contains("<"))
                calories = (-1) * Float.parseFloat(line.get(CALORIES_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(CALORIES_COLUMN).contains(">"))
                calories = Float.parseFloat(line.get(CALORIES_COLUMN).replace(",", ".").replace(">", ""));
            else
                calories = -1;
        }
        float fat;
        try {
            fat = Float.parseFloat(line.get(FAT_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(FAT_COLUMN).contains("<"))
                fat = (-1) * Float.parseFloat(line.get(FAT_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(FAT_COLUMN).contains(">"))
                fat = Float.parseFloat(line.get(FAT_COLUMN).replace(",", ".").replace(">", ""));
            else
                fat = -1;
        }
        float saturated_fatty_acids;
        try {
            saturated_fatty_acids = Float.parseFloat(line.get(SATURATED_FATTY_ACIDS_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(SATURATED_FATTY_ACIDS_COLUMN).contains("<"))
                saturated_fatty_acids = (-1) * Float.parseFloat(line.get(SATURATED_FATTY_ACIDS_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(SATURATED_FATTY_ACIDS_COLUMN).contains(">"))
                saturated_fatty_acids = Float.parseFloat(line.get(SATURATED_FATTY_ACIDS_COLUMN).replace(",", ".").replace(">", ""));
            else
                saturated_fatty_acids = -1;
        }
        float unsaturated_fatty_acids;
        try {
            unsaturated_fatty_acids = Float.parseFloat(line.get(UNSATURATED_FATTY_ACIDS_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(UNSATURATED_FATTY_ACIDS_COLUMN).contains("<"))
                unsaturated_fatty_acids = (-1) * Float.parseFloat(line.get(UNSATURATED_FATTY_ACIDS_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(UNSATURATED_FATTY_ACIDS_COLUMN).contains(">"))
                unsaturated_fatty_acids = Float.parseFloat(line.get(UNSATURATED_FATTY_ACIDS_COLUMN).replace(",", ".").replace(">", ""));
            else
                unsaturated_fatty_acids = -1;
        }
        float carbohydrates_all;
        try {
            carbohydrates_all = Float.parseFloat(line.get(CARBOHYDRATES_ALL_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(CARBOHYDRATES_ALL_COLUMN).contains("<"))
                carbohydrates_all = (-1) * Float.parseFloat(line.get(CARBOHYDRATES_ALL_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(CARBOHYDRATES_ALL_COLUMN).contains(">"))
                carbohydrates_all = Float.parseFloat(line.get(CARBOHYDRATES_ALL_COLUMN).replace(",", ".").replace(">", ""));
            else
                carbohydrates_all = -1;
        }
        float simple_sugars;
        try {
            simple_sugars = Float.parseFloat(line.get(SIMPLE_SUGARS_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(SIMPLE_SUGARS_COLUMN).contains("<"))
                simple_sugars = (-1) * Float.parseFloat(line.get(SIMPLE_SUGARS_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(SIMPLE_SUGARS_COLUMN).contains(">"))
                simple_sugars = Float.parseFloat(line.get(SIMPLE_SUGARS_COLUMN).replace(",", ".").replace(">", ""));
            else
                simple_sugars = -1;
        }
        float etoh;
        try {
            etoh = Float.parseFloat(line.get(ETOH_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(ETOH_COLUMN).contains("<"))
                etoh = (-1) * Float.parseFloat(line.get(ETOH_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(ETOH_COLUMN).contains(">"))
                etoh = Float.parseFloat(line.get(ETOH_COLUMN).replace(",", ".").replace(">", ""));
            else
                etoh = -1;
        }
        float h2o;
        try {
            h2o = Float.parseFloat(line.get(H2O_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(H2O_COLUMN).contains("<"))
                h2o = (-1) * Float.parseFloat(line.get(H2O_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(H2O_COLUMN).contains(">"))
                h2o = Float.parseFloat(line.get(H2O_COLUMN).replace(",", ".").replace(">", ""));
            else
                h2o = -1;
        }
        float table_salt;
        try {
            table_salt = Float.parseFloat(line.get(TABLE_SALT_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(TABLE_SALT_COLUMN).contains("<"))
                table_salt = (-1) * Float.parseFloat(line.get(TABLE_SALT_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(TABLE_SALT_COLUMN).contains(">"))
                table_salt = Float.parseFloat(line.get(TABLE_SALT_COLUMN).replace(",", ".").replace(">", ""));
            else
                table_salt = -1;
        }
        float sodium = 0;
        try {
            sodium = Float.parseFloat(line.get(SODIUM_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(SODIUM_COLUMN).contains("<"))
                sodium = (-1) * Float.parseFloat(line.get(SODIUM_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(SODIUM_COLUMN).contains(">"))
                sodium = Float.parseFloat(line.get(SODIUM_COLUMN).replace(",", ".").replace(">", ""));
            else
                sodium = -1;
        }
        float chlorine;
        try {
            chlorine = Float.parseFloat(line.get(CHLORINE_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(CHLORINE_COLUMN).contains("<"))
                chlorine = (-1) * Float.parseFloat(line.get(CHLORINE_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(CHLORINE_COLUMN).contains(">"))
                chlorine = Float.parseFloat(line.get(CHLORINE_COLUMN).replace(",", ".").replace(">", ""));
            else
                chlorine = -1;
        }
        float magnesium;
        try {
            magnesium = Float.parseFloat(line.get(MAGNESIUM_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(MAGNESIUM_COLUMN).contains("<"))
                magnesium = (-1) * Float.parseFloat(line.get(MAGNESIUM_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(MAGNESIUM_COLUMN).contains(">"))
                magnesium = Float.parseFloat(line.get(MAGNESIUM_COLUMN).replace(",", ".").replace(">", ""));
            else
                magnesium = -1;
        }
        float potassium;
        try {
            potassium = Float.parseFloat(line.get(POTASSIUM_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(POTASSIUM_COLUMN).contains("<"))
                potassium = (-1) * Float.parseFloat(line.get(POTASSIUM_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(POTASSIUM_COLUMN).contains(">"))
                potassium = Float.parseFloat(line.get(POTASSIUM_COLUMN).replace(",", ".").replace(">", ""));
            else
                potassium = -1;
        }
        float calcium;
        try {
            calcium = Float.parseFloat(line.get(CALCIUM_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(CALCIUM_COLUMN).contains("<"))
                calcium = (-1) * Float.parseFloat(line.get(CALCIUM_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(CALCIUM_COLUMN).contains(">"))
                calcium = Float.parseFloat(line.get(CALCIUM_COLUMN).replace(",", ".").replace(">", ""));
            else
                calcium = -1;
        }
        float phosphor;
        try {
            phosphor = Float.parseFloat(line.get(PHOSPHOR_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(PHOSPHOR_COLUMN).contains("<"))
                phosphor = (-1) * Float.parseFloat(line.get(PHOSPHOR_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(PHOSPHOR_COLUMN).contains(">"))
                phosphor = Float.parseFloat(line.get(PHOSPHOR_COLUMN).replace(",", ".").replace(">", ""));
            else
                phosphor = -1;
        }
        float iron;
        try {
            iron = Float.parseFloat(line.get(IRON_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(IRON_COLUMN).contains("<"))
                iron = (-1) * Float.parseFloat(line.get(IRON_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(IRON_COLUMN).contains(">"))
                iron = Float.parseFloat(line.get(IRON_COLUMN).replace(",", ".").replace(">", ""));
            else
                iron = -1;
        }
        float protein;
        try {
            protein = Float.parseFloat(line.get(PROTEIN_COLUMN).replace(",", "."));
        } catch (NumberFormatException e) {
            if (line.get(PROTEIN_COLUMN).contains("<"))
                protein = (-1) * Float.parseFloat(line.get(PROTEIN_COLUMN).replace(",", ".").replace("<", ""));
            else if (line.get(PROTEIN_COLUMN).contains(">"))
                protein = Float.parseFloat(line.get(PROTEIN_COLUMN).replace(",", ".").replace(">", ""));
            else
                protein = -1;
        }

        return new NutritionFactTable(
                nourishment_category, nourishment_name, nourishment_synonym, calories,
                fat, saturated_fatty_acids, unsaturated_fatty_acids, carbohydrates_all, simple_sugars, etoh, h2o,
                table_salt, sodium, chlorine, magnesium, potassium, calcium, phosphor, iron, protein);
    }

}
