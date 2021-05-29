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
        String line= br.readLine();
        Log.e(EXTRACT_CSV_TAG, line);
        char[] charLine = line.toCharArray();
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

    public float getDataCellFromLine(List<String> line, int position) {
        float cell;
        String cellString = line.get(position).replace(",", ".");
        try {
            cell = Float.parseFloat(cellString);
        } catch (NumberFormatException e) {
            if (cellString.contains("<"))
                cell = (-1) * Float.parseFloat(cellString.replace("<", ""));
            else if (cellString.contains(">"))
                cell = Float.parseFloat(cellString.replace(">", ""));
            else
                cell = -1;
        }
        return cell;
    }

    public String getStringCellFromLine(List<String> line, int position){
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
                getDataCellFromLine(line,H2O_COLUMN),
                getDataCellFromLine(line, TABLE_SALT_COLUMN),
                getDataCellFromLine(line,SODIUM_COLUMN ),
                getDataCellFromLine(line,CHLORINE_COLUMN ),
                getDataCellFromLine(line, MAGNESIUM_COLUMN),
                getDataCellFromLine(line, POTASSIUM_COLUMN),
                getDataCellFromLine(line, CALCIUM_COLUMN),
                getDataCellFromLine(line, PHOSPHOR_COLUMN),
                getDataCellFromLine(line, IRON_COLUMN),
                getDataCellFromLine(line, PROTEIN_COLUMN));
    }

}
