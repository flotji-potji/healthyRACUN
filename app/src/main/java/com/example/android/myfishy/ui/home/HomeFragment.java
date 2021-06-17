package com.example.android.myfishy.ui.home;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.myfishy.R;
import com.example.android.myfishy.db.entities.DietaryRestrictionTable;
import com.example.android.myfishy.db.entities.User;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private HorizontalBarChart barChart;

    private PieChart pieChart;
    private PieChart pieChartUser;
    private PieChart pieChartCalories;

    private TextView txt_iroVal;
    private TextView txt_calVal;
    private TextView txt_natVal;
    private TextView txt_magVal;
    private TextView txt_phoVal;
    private TextView txt_potVal;
    private TextView txt_cloVal;
    private TextView txt_sodVal;
    private TextView txt_calActual;
    private TextView txt_cal;
    private TextView txt_watVal;

    private HorizontalBarChart barChartNatrium;
    private HorizontalBarChart barChartCalcium;
    private HorizontalBarChart barChartIron;
    private HorizontalBarChart barChartMagensium;
    private HorizontalBarChart barChartPhosphor;
    private HorizontalBarChart barChartPotassium;
    private HorizontalBarChart barChartChlorine;
    private HorizontalBarChart barChartSodium;
    private HorizontalBarChart barchart_water;

    private String mParam1;
    private String mParam2;
    private String username;
    private String firstname;
    private String surname;
    private long birthday;
    private String gender;
    private float weight;
    private float height;
    private TextView txtusername;
    private XAxis xAxis;

    String condition_name;
    float table_salt;

    float potassiumMin;
    float potassiumMax;
    float natriumMin;
    float natriumMax;
    float calciumMin;
    float calciumMax;
    float waterMin;
    float waterMax;

    float chlorine;
    float magnesium;
    float potassium;
    float calcium;
    float phosphor;
    float iron;
    float sodium;
    float natrium;

    String diet_name;

    float protein;
    int calories;
    float liquid_intake;
    float carbs;
    float fats;
    float fibers;

    int caloriesmax;
    int caleaten;

    int consCalories;


    DietaryRestrictionTable drt = new DietaryRestrictionTable(condition_name, diet_name, table_salt, sodium, potassium, calcium, phosphor, protein, calories, liquid_intake, carbs, fats, fibers);

    User userinfo = new User(username, firstname, surname,birthday, gender, weight , height);




    private  void loadBarChartWater()
    {
        List<BarEntry> entries = new ArrayList<>();


        if(liquid_intake == 0)
        {
            liquid_intake = 600f;
        }

        if (drt.getCondition_name() == "CNI Stufe 5D" )
        {
            waterMin = 500;
            waterMax = 800;
        }
        else
        {
            waterMin = 2000f;
        }

        entries.add(new BarEntry(0f, liquid_intake));
        BarDataSet set = new BarDataSet(entries, "");

        if(liquid_intake > waterMax || liquid_intake < waterMin )
        {
            set.setColor(Color.RED);
        }
        else
        {
            set.setColor(Color.GREEN);
        }

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        LimitLine maXll = new LimitLine(waterMax, "Max");
        LimitLine miNll = new LimitLine(waterMin , "Min");
        maXll.setLineWidth(2f);
        miNll.setLineWidth(2f);
        maXll.setLineColor(Color.BLACK);
        miNll.setLineColor(Color.BLACK);

        if(waterMax != 0)
        {
            barchart_water.getAxisRight().addLimitLine(maXll);
        }
        barchart_water.getAxisRight().addLimitLine(miNll);

        barchart_water.setData(data);
        barchart_water.invalidate(); // refresh
    }
    private void loadBarChartPho()
    {
        List<BarEntry> entries = new ArrayList<>();

        if(phosphor == 0)
        {
            phosphor = 600f;
        }
//        else
//        {
//            phosphor = drt.getPhosphor();
//        }

        entries.add(new BarEntry(0f, phosphor));
        BarDataSet set = new BarDataSet(entries, "");


        if(phosphor > 700f)
        {
            set.setColor(Color.RED);
        }
        else if(phosphor < 700f)
        {
            set.setColor(Color.rgb( 152,251,152));
        }
        else
        {
            set.setColor(Color.GREEN);
        }

        //Setting new color, needs to be done

//        set.setColors(new int[] {Color.GRAY, Color.GREEN, Color.RED });

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        LimitLine maXll = new LimitLine(1200f, "Max");
        LimitLine miNll = new LimitLine(700f , "Min");
        maXll.setLineWidth(2f);
        miNll.setLineWidth(2f);
        maXll.setLineColor(Color.BLACK);
        miNll.setLineColor(Color.BLACK);
        barChartPhosphor.getAxisRight().addLimitLine(maXll);
        barChartPhosphor.getAxisRight().addLimitLine(miNll);

//        String[] yAxisLables = new String[]{"0","1", "2", "3"};
        barChartPhosphor.setData(data);
        barChartPhosphor.invalidate(); // refresh
    }
    private void loadBarChartMag()
    {

        if(magnesium == 0)
        {
            magnesium = 450f;
        }

        List<BarEntry> entries = new ArrayList<>();


        entries.add(new BarEntry(0f, magnesium));
        BarDataSet set = new BarDataSet(entries, "");

        //Setting new color, needs to be done
//        set.setColors(new int[] {Color.GRAY, Color.GREEN, Color.RED });

        if(magnesium > 420f)
        {
            set.setColor(Color.RED);
        }
        else if(magnesium < 310f)
        {
            set.setColor(Color.rgb( 152,251,152));
        }
        else
        {
            set.setColor(Color.GREEN);
        }

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        LimitLine maXll = new LimitLine(420f, "Max");
        LimitLine miNll = new LimitLine(310f, "Min");
        maXll.setLineWidth(2f);
        miNll.setLineWidth(2f);
        maXll.setLineColor(Color.BLACK);
        miNll.setLineColor(Color.BLACK);
        barChartMagensium.getAxisRight().addLimitLine(maXll);
        barChartMagensium.getAxisRight().addLimitLine(miNll);

//        String[] yAxisLables = new String[]{"0","1", "2", "3"};
        barChartMagensium.setData(data);
        barChartMagensium.invalidate(); // refresh
    }
    private void loadBarChartNat()
    {

        if(natrium == 0)
        {
            natrium = 120f;
        }


        List<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0f, natrium));
        BarDataSet set = new BarDataSet(entries, "");

        if (drt.getCondition_name() == "CNI Stufe 1-3a" || drt.getCondition_name()  == "CNI Stufe 3b-4" || drt.getCondition_name()  == "CNI Stufe 5"  || drt.getCondition_name()  == "CNI Stufe 5D")
        {
            natriumMax = 100f;
            natriumMin = 0;
        }
        else
        {
            natriumMax = 145f;
            natriumMin = 136f;
        }

        if(natrium > natriumMax || natrium < natriumMin)
        {
            set.setColor(Color.RED);
        }
        else
        {
            set.setColor(Color.GREEN);
        }

        LimitLine maXll = new LimitLine(natriumMax, "Max");
        LimitLine miNll = new LimitLine(natriumMin, "Min");
        maXll.setLineWidth(2f);
        miNll.setLineWidth(2f);
        maXll.setLineColor(Color.BLACK);
        miNll.setLineColor(Color.BLACK);
        barChartNatrium.getAxisRight().addLimitLine(maXll);
        barChartNatrium.getAxisRight().addLimitLine(miNll);

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);


        barChartNatrium.setData(data);
        barChartNatrium.invalidate(); // refresh
    }

    private void loadBarChartIron()
    {

        if(iron == 0)
        {
            iron = 3f;
        }

        List<BarEntry> entries = new ArrayList<>();


        entries.add(new BarEntry(0f, iron));
        BarDataSet set = new BarDataSet(entries, "");

        //Setting new color, needs to be done
//        set.setColors(new int[] {Color.GRAY, Color.GREEN, Color.RED });

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        if(iron > 45f)
        {
            set.setColor(Color.RED);
        }
        else if(iron < 8f)
        {
            set.setColor(Color.rgb( 152,251,152));
        }
        else
        {
            set.setColor(Color.GREEN);
        }

        LimitLine maXll = new LimitLine(45f, "Max");
        LimitLine miNll = new LimitLine(7.5f, "Min");
        maXll.setLineWidth(2f);
        miNll.setLineWidth(2f);
        maXll.setLineColor(Color.BLACK);
        miNll.setLineColor(Color.BLACK);
        barChartIron.getAxisRight().addLimitLine(maXll);
        barChartIron.getAxisRight().addLimitLine(miNll);



        barChartIron.setData(data);
        barChartIron.invalidate(); // refresh
    }

    private void loadBarChartPot()
    {
        if(potassium == 0)
        {
            potassium = 4000f;
        }

        List<BarEntry> entries = new ArrayList<>();


        entries.add(new BarEntry(0f, potassium));
        BarDataSet set = new BarDataSet(entries, "");
        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        if (drt.getCondition_name() == "CNI Stufe 1-3a" || drt.getCondition_name() == "CNI Stufe 3b-4" )
        {
            potassiumMin = 0;
            potassiumMin = 4700;
        }
        else if(drt.getCondition_name() == "CNI Stufe 5"  || drt.getCondition_name() == "CNI Stufe 5D")
        {
            potassiumMax = 2700f;
            potassiumMin = 0;
        }
        else
        {
            potassiumMax = 4700f;
            potassiumMin = 0;
        }


        if(potassium > potassiumMax || potassium < potassiumMin )
        {
            set.setColor(Color.RED);
        }
        else
        {
            set.setColor(Color.GREEN);
        }

        LimitLine maXll = new LimitLine(potassiumMax, "Max");
        LimitLine miNll = new LimitLine(potassiumMin, "Min");
        maXll.setLineWidth(2f);
        maXll.setLineColor(Color.BLACK);
        miNll.setLineWidth(2f);
        miNll.setLineColor(Color.BLACK);

        if(potassiumMax != 0)
        {
            barChartPotassium.getAxisRight().addLimitLine(maXll);
        }
        if(potassiumMin != 0)
        {
            barChartPotassium.getAxisRight().addLimitLine(miNll);
        }


        barChartPotassium.setData(data);
        barChartPotassium.invalidate(); // refresh
    }

    private void loadBarChartCal()
    {
        List<BarEntry> entries = new ArrayList<>();

        if(calcium == 0)
        {
            calcium = 1800f;
        }



        entries.add(new BarEntry(0f, calcium));
        BarDataSet set = new BarDataSet(entries, "");

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        if (drt.getCondition_name() == "CNI Stufe 3b-4" || drt.getCondition_name() == "CNI Stufe 5"  || drt.getCondition_name() == "CNI Stufe 5D" )
        {
            calciumMax = 2000;
        }
        else
        {
            calciumMax = 2500f;
            calciumMin = 1000f;
        }

        if(calcium > calciumMax)
        {
            set.setColor(Color.RED);
        }
        else if(calcium < calciumMin)
        {
            set.setColor(Color.rgb( 152,251,152));
        }
        else
        {
            set.setColor(Color.GREEN);
        }

        LimitLine maXll = new LimitLine(calciumMax, "Max");
        LimitLine miNll = new LimitLine(calciumMin , "Min");
        maXll.setLineWidth(2f);
        miNll.setLineWidth(2f);
        maXll.setLineColor(Color.BLACK);
        miNll.setLineColor(Color.BLACK);

        if(calciumMin != 0)
        {
            barChartCalcium.getAxisRight().addLimitLine(miNll);
        }
        barChartCalcium.getAxisRight().addLimitLine(maXll);



        barChartCalcium.setData(data);
        barChartCalcium.invalidate(); // refresh
    }

    private void loadBarChartChlorine()
    {
        List<BarEntry> entries = new ArrayList<>();


        if(chlorine == 0)
        {
            chlorine = 2.5f;
        }



        entries.add(new BarEntry(0f, chlorine));
        BarDataSet set = new BarDataSet(entries, "");

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        if(chlorine > 3f)
        {
            set.setColor(Color.RED);
        }
        else if(chlorine < 1f)
        {
            set.setColor(Color.rgb( 152,251,152));
        }
        else
        {
            set.setColor(Color.GREEN);
        }

        LimitLine maXll = new LimitLine(3f, "Max");
        LimitLine miNll = new LimitLine(1f , "Min");
        maXll.setLineWidth(2f);
        miNll.setLineWidth(2f);
        maXll.setLineColor(Color.BLACK);
        miNll.setLineColor(Color.BLACK);
        barChartChlorine.getAxisRight().addLimitLine(maXll);
        barChartChlorine.getAxisRight().addLimitLine(miNll);


        barChartChlorine.setData(data);
        barChartChlorine.invalidate(); // refresh
    }

    private void loadBarChartSod()
    {
        List<BarEntry> entries = new ArrayList<>();


        if(sodium == 0)
        {
            sodium = 1600f;
        }



        entries.add(new BarEntry(0f, sodium));
        BarDataSet set = new BarDataSet(entries, "");

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        if(sodium > 2300f)
        {
            set.setColor(Color.RED);
        }
        else if(sodium < 1500f)
        {
            set.setColor(Color.rgb( 152,251,152));
        }
        else
        {
            set.setColor(Color.GREEN);
        }

        LimitLine maXll = new LimitLine(2300f, "Max");
        LimitLine miNll = new LimitLine(1500f , "Min");
        maXll.setLineWidth(2f);
        miNll.setLineWidth(2f);
        maXll.setLineColor(Color.BLACK);
        miNll.setLineColor(Color.BLACK);
        barChartSodium.getAxisRight().addLimitLine(maXll);
        barChartSodium.getAxisRight().addLimitLine(miNll);


        barChartSodium.setData(data);
        barChartSodium.invalidate(); // refresh
    }

    private void setBarChart()
    {

        barChartPotassium.getAxisRight().setAxisMinimum(0f);
        barChartPotassium.getAxisLeft().setAxisMinimum(0f);
        barChartPotassium.getAxisRight().setAxisMaximum(8000f);
        barChartPotassium.getAxisLeft().setAxisMaximum(8000f);
        barChartPotassium.getAxisLeft().setEnabled(false);
        barChartPotassium.animateY(1100);
        barChartPotassium.getAxisRight().setLabelCount(6, true);
        barChartPotassium.getAxisLeft().setLabelCount(6,true);

        barChartPotassium.getXAxis().setEnabled(false);

        barChartPotassium.getAxisRight().setDrawAxisLine(false);
        barChartPotassium.getLegend().setEnabled(false);
        barChartPotassium.getDescription().setEnabled(false);

        //no zoom
        barChartPotassium.setPinchZoom(false);
        barChartPotassium.setTouchEnabled(false);
        barChartPotassium.setDoubleTapToZoomEnabled(false);

        barChartPhosphor.getAxisRight().setAxisMinimum(0f);
        barChartPhosphor.getAxisLeft().setAxisMinimum(0f);
        barChartPhosphor.getAxisRight().setAxisMaximum(2000f);
        barChartPhosphor.getAxisLeft().setAxisMaximum(2000f);
        barChartPhosphor.getAxisLeft().setEnabled(false);
        barChartPhosphor.animateY(1100);
        barChartPhosphor.getAxisRight().setLabelCount(6, true);
        barChartPhosphor.getAxisLeft().setLabelCount(6,true);

        barChartPhosphor.getXAxis().setEnabled(false);

        barChartPhosphor.getAxisRight().setDrawAxisLine(false);
        barChartPhosphor.getLegend().setEnabled(false);
        barChartPhosphor.getDescription().setEnabled(false);

        //no zoom
        barChartPhosphor.setPinchZoom(false);
        barChartPhosphor.setTouchEnabled(false);
        barChartPhosphor.setDoubleTapToZoomEnabled(false);

        barChartMagensium.getAxisRight().setAxisMinimum(0f);
        barChartMagensium.getAxisLeft().setAxisMinimum(0f);
        barChartMagensium.getAxisRight().setAxisMaximum(600f);
        barChartMagensium.getAxisLeft().setAxisMaximum(600f);
        barChartMagensium.getAxisLeft().setEnabled(false);
        barChartMagensium.animateY(1100);
        barChartMagensium.getAxisRight().setLabelCount(6, true);
        barChartMagensium.getAxisLeft().setLabelCount(6,true);

        barChartMagensium.getXAxis().setEnabled(false);

        barChartMagensium.getAxisRight().setDrawAxisLine(false);
        barChartMagensium.getLegend().setEnabled(false);
        barChartMagensium.getDescription().setEnabled(false);

        //no zoom
        barChartMagensium.setPinchZoom(false);
        barChartMagensium.setTouchEnabled(false);
        barChartMagensium.setDoubleTapToZoomEnabled(false);

        barChartIron.getAxisRight().setAxisMinimum(0f);
        barChartIron.getAxisLeft().setAxisMinimum(0f);
        barChartIron.getAxisRight().setAxisMaximum(60f);
        barChartIron.getAxisLeft().setAxisMaximum(60f);
        barChartIron.getAxisLeft().setEnabled(false);
        barChartIron.animateY(1100);
        barChartIron.getAxisRight().setLabelCount(6, true);
        barChartIron.getAxisLeft().setLabelCount(6,true);




        barChartIron.getXAxis().setEnabled(false);

        barChartIron.getAxisRight().setDrawAxisLine(false);
        barChartIron.getLegend().setEnabled(false);
        barChartIron.getDescription().setEnabled(false);

        barChartIron.setPinchZoom(false);
        barChartIron.setTouchEnabled(false);
        barChartIron.setDoubleTapToZoomEnabled(false);



        //no zoom
        barChartNatrium.setPinchZoom(false);
        barChartNatrium.setTouchEnabled(false);
        barChartNatrium.setDoubleTapToZoomEnabled(false);


        barChartNatrium.getAxisRight().setAxisMinimum(80f);
        barChartNatrium.getAxisLeft().setAxisMinimum(80f);
        barChartNatrium.getAxisRight().setAxisMaximum(150f);
        barChartNatrium.getAxisLeft().setAxisMaximum(150f);
        barChartNatrium.getAxisLeft().setEnabled(false);
        barChartNatrium.animateY(1100);
        barChartNatrium.getAxisRight().setLabelCount(6, true);
        barChartNatrium.getAxisLeft().setLabelCount(6,true);

        barChartNatrium.getXAxis().setEnabled(false);

        barChartNatrium.getAxisRight().setDrawAxisLine(false);
        barChartNatrium.getLegend().setEnabled(false);
        barChartNatrium.getDescription().setEnabled(false);



            //no zoom
        barChartNatrium.setPinchZoom(false);
        barChartNatrium.setTouchEnabled(false);
        barChartNatrium.setDoubleTapToZoomEnabled(false);

        barChartCalcium.getAxisRight().setAxisMinimum(0f);
        barChartCalcium.getAxisLeft().setAxisMinimum(0f);
        barChartCalcium.getAxisRight().setAxisMaximum(3000f);
        barChartCalcium.getAxisLeft().setAxisMaximum(3000f);
        barChartCalcium.getAxisLeft().setEnabled(false);
        barChartCalcium.animateY(1100);
        barChartCalcium.getAxisRight().setLabelCount(6, true);
        barChartCalcium.getAxisLeft().setLabelCount(6,true);

        barChartCalcium.getXAxis().setEnabled(false);

        barChartCalcium.getAxisRight().setDrawAxisLine(false);
        barChartCalcium.getLegend().setEnabled(false);
        barChartCalcium.getDescription().setEnabled(false);

        //no zoom
        barChartCalcium.setPinchZoom(false);
        barChartCalcium.setTouchEnabled(false);
        barChartCalcium.setDoubleTapToZoomEnabled(false);


        barChartChlorine.getAxisRight().setAxisMinimum(0f);
        barChartChlorine.getAxisLeft().setAxisMinimum(0f);
        barChartChlorine.getAxisRight().setAxisMaximum(4f);
        barChartChlorine.getAxisLeft().setAxisMaximum(4f);
        barChartChlorine.getAxisLeft().setEnabled(false);
        barChartChlorine.animateY(1100);
        barChartChlorine.getAxisRight().setLabelCount(6, true);
        barChartChlorine.getAxisLeft().setLabelCount(6,true);

        barChartChlorine.getXAxis().setEnabled(false);

        barChartChlorine.getAxisRight().setDrawAxisLine(false);
        barChartChlorine.getLegend().setEnabled(false);
        barChartChlorine.getDescription().setEnabled(false);

        //no zoom
        barChartChlorine.setPinchZoom(false);
        barChartChlorine.setTouchEnabled(false);
        barChartChlorine.setDoubleTapToZoomEnabled(false);

        barChartSodium.getAxisRight().setAxisMinimum(0f);
        barChartSodium.getAxisLeft().setAxisMinimum(0f);
        barChartSodium.getAxisRight().setAxisMaximum(3500f);
        barChartSodium.getAxisLeft().setAxisMaximum(3500f);
        barChartSodium.getAxisLeft().setEnabled(false);
        barChartSodium.animateY(1100);
        barChartSodium.getAxisRight().setLabelCount(6, true);
        barChartSodium.getAxisLeft().setLabelCount(6,true);

        barChartSodium.getXAxis().setEnabled(false);

        barChartSodium.getAxisRight().setDrawAxisLine(false);
        barChartSodium.getLegend().setEnabled(false);
        barChartSodium.getDescription().setEnabled(false);

        //no zoom
        barChartSodium.setPinchZoom(false);
        barChartSodium.setTouchEnabled(false);
        barChartSodium.setDoubleTapToZoomEnabled(false);

        barchart_water.getAxisRight().setAxisMinimum(0f);
        barchart_water.getAxisLeft().setAxisMinimum(0f);
        barchart_water.getAxisRight().setAxisMaximum(3500f);
        barchart_water.getAxisLeft().setAxisMaximum(3500f);
        barchart_water.getAxisLeft().setEnabled(false);
        barchart_water.animateY(1100);
        barchart_water.getAxisRight().setLabelCount(6, true);
        barchart_water.getAxisLeft().setLabelCount(6,true);

        barchart_water.getXAxis().setEnabled(false);

        barchart_water.getAxisRight().setDrawAxisLine(false);
        barchart_water.getLegend().setEnabled(false);
        barchart_water.getDescription().setEnabled(false);

        //no zoom
        barchart_water.setPinchZoom(false);
        barchart_water.setTouchEnabled(false);
        barchart_water.setDoubleTapToZoomEnabled(false);



    }

    private void setupPieChart(){

        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getLegend().setEnabled(false);
        pieChart.getDescription().setEnabled(false);


        pieChartUser.setDrawHoleEnabled(true);
        pieChartUser.setUsePercentValues(true);
        pieChartUser.setEntryLabelTextSize(12);
        pieChartUser.setEntryLabelColor(Color.BLACK);
        pieChartUser.getLegend().setEnabled(false);
        pieChartUser.getDescription().setEnabled(false);



        pieChartCalories.setDrawHoleEnabled(true);
        pieChartCalories.setUsePercentValues(true);
        pieChartCalories.setDrawCenterText(true);
        pieChartCalories.getLegend().setEnabled(false);
        pieChartCalories.setDrawEntryLabels(false);
        pieChartCalories.getDescription().setEnabled(false);
        pieChartCalories.setHoleRadius(80);


        pieChartCalories.setMaxAngle(260.0f);
        pieChartCalories.setRotationAngle(140.0f);


        pieChartCalories.setTouchEnabled(false);
        pieChartUser.setTouchEnabled(false);
        pieChart.setTouchEnabled(false);
    }


    private void loadPieChart()
    {
        ArrayList<PieEntry> entries = new ArrayList<>();

//        weight = userinfo.getWeight();
        weight = userinfo.getWeight();
        if(weight == 0)
        {
            weight = 80;
        }


        //value: daten die mitgegeben werden, um anzuzeigen wv der nährstoffe eingenommen wurdem


        condition_name = drt.getCondition_name();
        if(condition_name == null)
        {
            condition_name = "";
        }

        switch (condition_name)
        {
            case "CNI Stufe 1-3a" :
                entries.add(new PieEntry(0.55f, "Carbs"));
                entries.add(new PieEntry((float) ((weight*0.8)/100), "Proteins"));
                entries.add(new PieEntry(0.28f, "Fats"));
                break;
            case "CNI Stufe 3b-4":
            case "CNI Stufe 5":
                entries.add(new PieEntry(0.55f, "Carbs"));
                entries.add(new PieEntry((float) ((weight*1)/100), "Proteins"));
                entries.add(new PieEntry(0.28f, "Fats"));
                break;

            case "CNI Stufe 5D":
                entries.add(new PieEntry(0.55f, "Carbs"));
                entries.add(new PieEntry((float) ((weight*1.1)/100), "Proteins"));
                entries.add(new PieEntry(0.28f, "Fats"));

                break;
            case "Typ 1":
            case "Typ 2":
                entries.add(new PieEntry(0.55f, "Carbs"));
                entries.add(new PieEntry(0.17f, "Proteins"));
                entries.add(new PieEntry(0.28f, "Fats"));

                break;
            case "Morbus Crohn":
            case "Colitis Ulc.":
                entries.add(new PieEntry(0.48f, "Carbs"));
                entries.add(new PieEntry(0.17f, "Proteins"));
                entries.add(new PieEntry(0.35f, "Fats"));
                break;
            default:
                entries.add(new PieEntry(0.55f, "Carbs"));
                entries.add(new PieEntry(0.20f, "Proteins"));
                entries.add(new PieEntry(0.25f, "Fats"));
        }

        //colors liste wurde erstellt um die entries in farben einzukategorisieren.
        int[] colors = { Color.rgb(0,255,0), Color.rgb(255,140,0), Color.rgb(255,0,0)};

        PieDataSet dataSet = new PieDataSet(entries, "Nutritions:");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
        pieChart.animateY(600, Easing.EaseInOutQuad);

    }

    private void loadPieChartUser()
    {
        ArrayList<PieEntry> entries = new ArrayList<>();



        //value: daten die mitgegeben werden, um anzuzeigen wv der nährstoffe eingenommen wurdem

        entries.add(new PieEntry(0.55f, "Carbs"));
        entries.add(new PieEntry(0.20f, "Proteins"));
        entries.add(new PieEntry(0.25f, "Fats"));

        //colors liste wurde erstellt um die entries in farben einzukategorisieren.
        int[] colors = { Color.rgb(0,255,0), Color.rgb(255,140,0), Color.rgb(255,0,0)};

        PieDataSet dataSet = new PieDataSet(entries, "Nutritions:");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChartUser));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChartUser.setData(data);
        pieChartUser.invalidate();
        pieChartUser.animateY(600, Easing.EaseInOutQuad);

    }

    private void loadPieChartCalories()
    {
        ArrayList<PieEntry> entries = new ArrayList<>();

        weight = userinfo.getWeight();

        if(weight == 0)
        {
            weight = 80;
        }


        //value: daten die mitgegeben werden, um anzuzeigen wv der nährstoffe eingenommen wurdem

        if (drt.getCondition_name() == "CNI Stufe 1-3a" )
        {
            caleaten = 1500;
            caloriesmax = (int) (35*weight);
        }
        else if(drt.getCondition_name()  == "CNI Stufe 3b-4" || drt.getCondition_name()  == "CNI Stufe 5"  || drt.getCondition_name()  == "CNI Stufe 5D")
        {
            caleaten = 1500;
            caloriesmax = (int) (40*weight);
        }
        else
        {
            caloriesmax = 2000;
            caleaten = 1500;
        }

        entries.add(new PieEntry(caleaten, "eaten"));
        entries.add(new PieEntry(caloriesmax-caleaten , "max cal"));



        //colors liste wurde erstellt um die entries in farben einzukategorisieren.
        int[] colors = { Color.rgb(0,255,255), Color.rgb(220,220,220)};

        PieDataSet dataSet = new PieDataSet(entries, "Nutritions:");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setDrawValues(false);
        data.setValueFormatter(new PercentFormatter(pieChartCalories));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);


        pieChartCalories.setCenterText(Integer.toString(caleaten) +"/"+ Integer.toString(caloriesmax) + "kcal");
        pieChartCalories.setCenterTextSize(22);
        pieChartCalories.setData(data);
        pieChartCalories.invalidate();
        pieChartCalories.animateY(600, Easing.EaseInOutQuad);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);


        txtusername = (TextView) root.findViewById(R.id.home_username);


        if(username == null)
        {
            txtusername.setText("Sascha Karottenmann");
        }

        txt_watVal = (TextView) root.findViewById(R.id.txt_watVal);
        if(liquid_intake == 0)
        {
            liquid_intake = 600f;
            txt_watVal.setText(Float.toString(liquid_intake));
        }

        txt_iroVal = (TextView) root.findViewById(R.id.txt_iroVal);
        if(iron == 0)
        {
            iron = 3f;
            txt_iroVal.setText(Float.toString(iron));
        }

        txt_calVal = (TextView) root.findViewById(R.id.txt_calVal);
        if(calcium == 0)
        {
            calcium = 1800f;
            txt_calVal.setText(Float.toString(calcium));
        }

        txt_magVal = (TextView) root.findViewById(R.id.txt_magVal);
        if(magnesium == 0)
        {
            magnesium = 450f;
            txt_magVal.setText(Float.toString(magnesium));
        }

        txt_phoVal = (TextView) root.findViewById(R.id.txt_phoVal);
        if(phosphor == 0)
        {
            phosphor = 600f;
            txt_phoVal.setText(Float.toString(phosphor));
        }

        txt_potVal = (TextView) root.findViewById(R.id.txt_potVal);
        if(potassium == 0)
        {
            potassium = 4000f;
            txt_potVal.setText(Float.toString(potassium));
        }

        txt_natVal = (TextView) root.findViewById(R.id.txt_natVal);
        if(natrium == 0)
        {
            natrium = 120f;
            txt_natVal.setText(Float.toString(natrium));
        }
        txt_cloVal = (TextView) root.findViewById(R.id.txt_cloVal);
        if(chlorine == 0)
        {
            chlorine = 2.5f;
            txt_cloVal.setText(Float.toString(chlorine));
        }

        txt_sodVal = (TextView) root.findViewById(R.id.txt_sodVal);
        if(sodium == 0)
        {
            sodium = 1600f;
            txt_sodVal.setText(Float.toString(sodium));
        }







        barChartNatrium = root.findViewById(R.id.barchart_natrium);
        barChartCalcium = root.findViewById(R.id.barchart_calcium);
        barChartIron = root.findViewById(R.id.barchart_iron);
        barChartMagensium = root.findViewById(R.id.barchart_magnesium);
        barChartPhosphor = root.findViewById(R.id.barchart_phosphor);
        barChartPotassium = root.findViewById(R.id.barchart_potassium);
        barChartChlorine = root.findViewById(R.id.barchart_chlorine);
        barChartSodium = root.findViewById(R.id.barchart_Sodium);
        barchart_water = root.findViewById(R.id.barchart_water);

        pieChart = root.findViewById(R.id.piechart_standard);
        pieChartUser = root.findViewById(R.id.piechart_user);
        pieChartCalories = root.findViewById(R.id.piechart_calories);

        setBarChart();
        setupPieChart();
        loadBarChartWater();
        loadBarChartPot();
        loadBarChartCal();
        loadBarChartIron();
        loadBarChartNat();
        loadBarChartMag();
        loadBarChartPho();
        loadBarChartChlorine();
        loadBarChartSod();

        loadPieChartCalories();
        loadPieChartUser();
        loadPieChart();

        return root;
    }
}