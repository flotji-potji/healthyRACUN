package com.example.android.myfishy.utilities;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.example.android.myfishy.R;
import com.example.android.myfishy.db.entities.Diet;
import com.example.android.myfishy.db.entities.DietaryRestrictionTable;
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
    private final short FIBERS_COLUMN = 38;

    private final short CONDITION_NAME = 0;
    private final short DIET_NAME = 1;
    private final short DIET_TABLE_SALT = 2;
    private final short DIET_SODIUM = 3;
    private final short DIET_LIQUID_INTAKE = 4;
    private final short DIET_POTASSIUM = 5;
    private final short DIET_PHOSPHATE = 6;
    private final short DIET_CALCIUM = 7;
    private final short DIET_CALORIES = 8;
    private final short DIET_PROTEIN = 9;
    private final short DIET_CARBS = 10;
    private final short DIET_FATS = 11;
    private final short DIET_FIBERS = 12;

    public ExtractCSV(InputStreamReader isr) throws IOException {
        br = new BufferedReader(isr);
    }

    public void closeBr() throws IOException {
        br.close();
    }

    public List<String> next() throws IOException {
        String line = br.readLine();
        List<String> res = new ArrayList<>();
        char[] charLine;
        if (line != null)
            charLine = line.toCharArray();
        else
            return res;
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

    private float getDataCellFromLine(List<String> line, int position) {
        float cell;
        String cellString = line.get(position).replace(",", ".");
        try {
            cell = Float.parseFloat(cellString);
        } catch (NumberFormatException e) {
            if (cellString.contains("<"))
                cell = (-1) * Float.parseFloat(cellString.replace("<", "").trim());
            else if (cellString.contains(">"))
                cell = Float.parseFloat(cellString.replace(">", "").trim());
            else if (cellString.contains("%"))
                cell = Float.parseFloat(cellString.replace("%", "").trim());
            else
                cell = -1;
        }
        return cell;
    }

    private String getStringCellFromLine(List<String> line, int position) {
        return !line.get(position).isEmpty() ? line.get(position) : null;
    }

    public NutritionFactTable getNutritionFactTableRow(List<String> line) {
        return new NutritionFactTable(
                getStringCellFromLine(line, NOURISHMENT_CATEGORY_COLUMN),
                getStringCellFromLine(line, NOURISHMENT_NAME_COLUMN),
                getStringCellFromLine(line, NOURISHMENT_SYNONYM_COLUMN),
                getDataCellFromLine(line, CALORIES_COLUMN),
                getDataCellFromLine(line, FAT_COLUMN),
                getDataCellFromLine(line, SATURATED_FATTY_ACIDS_COLUMN),
                getDataCellFromLine(line, UNSATURATED_FATTY_ACIDS_COLUMN),
                getDataCellFromLine(line, CARBOHYDRATES_ALL_COLUMN),
                getDataCellFromLine(line, SIMPLE_SUGARS_COLUMN),
                getDataCellFromLine(line, ETOH_COLUMN),
                getDataCellFromLine(line, H2O_COLUMN),
                getDataCellFromLine(line, TABLE_SALT_COLUMN),
                getDataCellFromLine(line, SODIUM_COLUMN),
                getDataCellFromLine(line, CHLORINE_COLUMN),
                getDataCellFromLine(line, MAGNESIUM_COLUMN),
                getDataCellFromLine(line, POTASSIUM_COLUMN),
                getDataCellFromLine(line, CALCIUM_COLUMN),
                getDataCellFromLine(line, PHOSPHOR_COLUMN),
                getDataCellFromLine(line, IRON_COLUMN),
                getDataCellFromLine(line, PROTEIN_COLUMN),
                getDataCellFromLine(line, FIBERS_COLUMN)
        );
    }

    public DietaryRestrictionTable getDietaryRestrictionTableRow(List<String> line) {
        return new DietaryRestrictionTable(
                getStringCellFromLine(line, CONDITION_NAME),
                getStringCellFromLine(line, DIET_NAME),
                getDataCellFromLine(line, DIET_TABLE_SALT),
                getDataCellFromLine(line, DIET_SODIUM),
                getDataCellFromLine(line, DIET_POTASSIUM),
                getDataCellFromLine(line, DIET_CALCIUM),
                getDataCellFromLine(line, DIET_PHOSPHATE),
                getDataCellFromLine(line, DIET_PROTEIN),
                getDataCellFromLine(line, DIET_CALORIES),
                getDataCellFromLine(line, DIET_LIQUID_INTAKE),
                getDataCellFromLine(line, DIET_CARBS),
                getDataCellFromLine(line, DIET_FATS),
                getDataCellFromLine(line, DIET_FIBERS)
        );
    }

}
