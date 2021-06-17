package com.example.android.myfishy.utilities;

import androidx.annotation.NonNull;
import com.example.android.myfishy.db.entities.Diet;
import org.jetbrains.annotations.NotNull;

public class DietComparator extends Diet {

    public DietComparator(Diet d) {
        super(
                d.getDiet_id(),
                d.getUsername(),
                d.getCondition_name(),
                d.getDiet_name(),
                d.getTable_salt(),
                d.getSodium(),
                d.getPotassium(),
                d.getCalcium(),
                d.getPhosphor(),
                d.getProtein(),
                d.getCalories(),
                d.getLiquid_intake(),
                d.getCarbs(),
                d.getFats(),
                d.getFibers());
    }

    private float[] compareCIKD(Diet diet) {
        float[] res = new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        if (diet.getTable_salt() < 0)
            res[0] = Math.abs(diet.getTable_salt()) - this.getTable_salt();
        if (diet.getSodium() < 0)
            res[1] = Math.abs(diet.getSodium()) - this.getSodium();
        if (diet.getPotassium() < 0)
            res[2] = Math.abs(diet.getPotassium()) - this.getPotassium();
        else
            res[2] = this.getPotassium() - diet.getPotassium();
        if (diet.getCalcium() < 0)
            res[3] = Math.abs(diet.getCalcium()) - this.getCalcium();
        else if (diet.getCalcium() == -1)
            res[3] = 0;
        if (diet.getProtein() > 0)
            res[5] = this.getProtein() - Math.abs(diet.getProtein());
        if (diet.getCalories() < 0)
            res[6] = Math.abs(diet.getCalories()) - this.getCalories();
        if (diet.getLiquid_intake() < 0)
            res[7] = Math.abs(diet.getLiquid_intake()) - this.getLiquid_intake();
        else if (diet.getLiquid_intake() == -1)
            res[7] = 0;
        return res;
    }

    public float[] compareTo(Object o) {
        Diet diet = (Diet) o;
        switch (diet.getDiet_id()) {
            case 1:
            case 2:
            case 3:
            case 4:
                return compareCIKD(diet);
        }
        return new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }
}
