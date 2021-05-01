package com.example.android.myfishy.utilities;

import android.util.Pair;
import com.example.android.myfishy.db.entities.NutritionFactTable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExtractCSV {

    private static BufferedReader br;

    public ExtractCSV(InputStream is) throws FileNotFoundException {
        br = new BufferedReader(new InputStreamReader(is));
    }

    public static List<String> next() throws IOException {
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

    public static NutritionFactTable getNutritionFactTableRow(List<String> line) {
        String nourishment_category = "";
        String nourishment_name = "";
        String nourishment_synonym = "";
        float calories = 0;
        float fat = 0;
        float saturated_fatty_acids = 0;
        float unsaturated_fatty_acids = 0;
        float carbohydrates_all = 0;
        float simple_sugars = 0;
        float etoh = 0;
        float h20 = 0;
        float table_salt = 0;
        float sodium = 0;
        float chlorine = 0;
        float magnesium = 0;
        float potassium = 0;
        float calcium = 0;
        float phosphor = 0;
        float iron = 0;
        float protein = 0;


        for (int i = 0; i < line.size(); i++) {
            switch (i) {
                case 3:
                    nourishment_name = line.get(i);
                    break;
                case 4:
                    nourishment_synonym = line.get(i);
                    break;
                case 5:
                    nourishment_category = line.get(i);
                    break;
                case 8:
                    calories = Float.parseFloat(line.get(i));
                    break;
                case 14:
                    fat = Float.parseFloat(line.get(i));
            }
        }
        return new NutritionFactTable(
                nourishment_category, nourishment_name, nourishment_synonym, calories,
                fat, saturated_fatty_acids, unsaturated_fatty_acids, carbohydrates_all, simple_sugars, etoh, h20,
                table_salt, sodium, chlorine, magnesium, potassium, calcium, phosphor, iron, protein);
    }

}
