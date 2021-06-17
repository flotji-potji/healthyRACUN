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
import com.example.android.myfishy.db.entities.User;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
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



    private HorizontalBarChart barChartNatrium;
    private HorizontalBarChart barChartCalcium;
    private HorizontalBarChart barChartIron;
    private HorizontalBarChart barChartMagensium;
    private HorizontalBarChart barChartPhosphor;
    private HorizontalBarChart barChartPotassium;

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

    User userinfo = new User(username, firstname, surname,birthday, gender,weight,height);

    private void loadBarChartPho()
    {
        List<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0f, 1800f));
        BarDataSet set = new BarDataSet(entries, "");

        //Setting new color, needs to be done
        set.setColors(new int[] {Color.GRAY, Color.GREEN, Color.RED });

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        String[] yAxisLables = new String[]{"0","1", "2", "3"};
        barChartPhosphor.setData(data);
        barChartPhosphor.invalidate(); // refresh
    }
    private void loadBarChartMag()
    {
        List<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0f, 400f));
        BarDataSet set = new BarDataSet(entries, "");

        //Setting new color, needs to be done
        set.setColors(new int[] {Color.GRAY, Color.GREEN, Color.RED });

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        String[] yAxisLables = new String[]{"0","1", "2", "3"};
        barChartMagensium.setData(data);
        barChartMagensium.invalidate(); // refresh
    }
    private void loadBarChartNat()
    {
        List<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0f, 1800f));
        BarDataSet set = new BarDataSet(entries, "");

        //Setting new color, needs to be done
        set.setColors(new int[] {Color.GRAY, Color.GREEN, Color.RED });

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        String[] yAxisLables = new String[]{"0","1", "2", "3"};
        barChartNatrium.setData(data);
        barChartNatrium.invalidate(); // refresh
    }

    private void loadBarChartIron()
    {
        List<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0f, 1800f));
        BarDataSet set = new BarDataSet(entries, "");

        //Setting new color, needs to be done
        set.setColors(new int[] {Color.GRAY, Color.GREEN, Color.RED });

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        String[] yAxisLables = new String[]{"0","1", "2", "3"};
        barChartIron.setData(data);
        barChartIron.invalidate(); // refresh
    }

    private void loadBarChartPot()
    {
        List<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0f, 1800f));
        BarDataSet set = new BarDataSet(entries, "");

        //Setting new color, needs to be done
        set.setColors(new int[] {Color.GRAY, Color.GREEN, Color.RED });

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        String[] yAxisLables = new String[]{"0","1", "2", "3"};
        barChartPotassium.setData(data);
        barChartPotassium.invalidate(); // refresh
    }

    private void loadBarChartCal()
    {
        List<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0f, 1800f));
        BarDataSet set = new BarDataSet(entries, "");

        //Setting new color, needs to be done
        set.setColors(new int[] {Color.GRAY, Color.GREEN, Color.RED });

        BarData data = new BarData(set);

        data.setBarWidth(0.5f); // set custom bar width
        set.setDrawValues(false);

        String[] yAxisLables = new String[]{"0","1", "2", "3"};
        barChartCalcium.setData(data);
        barChartCalcium.invalidate(); // refresh
    }

    private void setBarChart()
    {


//        ArrayList<HorizontalBarChart> barChartList = new ArrayList<>();

//        private HorizontalBarChart barChartNatrium;
//        private HorizontalBarChart barChartCalcium;
//        private HorizontalBarChart barChartIron;
//        private HorizontalBarChart barChartMagensium;
//        private HorizontalBarChart barChartPhosphor;
//        private HorizontalBarChart barChartPotassium;

//        HorizontalBarChart[] horizontalBarChart = new HorizontalBarChart[]{barChartNatrium, barChartCalcium, barChartIron, barChartMagensium, barChartPhosphor, barChartPotassium };

        barChartPotassium.getAxisRight().setAxisMinimum(0f);
        barChartPotassium.getAxisLeft().setAxisMinimum(0f);
        barChartPotassium.getAxisRight().setAxisMaximum(8000f);
        barChartPotassium.getAxisLeft().setAxisMaximum(8000f);
        barChartPotassium.getAxisLeft().setEnabled(false);
        barChartPotassium.animateY(1100);
        barChartPotassium.getAxisRight().setLabelCount(9, true);
        barChartPotassium.getAxisLeft().setLabelCount(9,true);

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
        barChartPhosphor.getAxisRight().setAxisMaximum(4000f);
        barChartPhosphor.getAxisLeft().setAxisMaximum(4000f);
        barChartPhosphor.getAxisLeft().setEnabled(false);
        barChartPhosphor.animateY(1100);
        barChartPhosphor.getAxisRight().setLabelCount(9, true);
        barChartPhosphor.getAxisLeft().setLabelCount(9,true);

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
        barChartMagensium.getAxisRight().setLabelCount(9, true);
        barChartMagensium.getAxisLeft().setLabelCount(9,true);

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
        barChartIron.getAxisRight().setLabelCount(9, true);
        barChartIron.getAxisLeft().setLabelCount(9,true);

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


        barChartNatrium.getAxisRight().setAxisMinimum(0f);
        barChartNatrium.getAxisLeft().setAxisMinimum(0f);
        barChartNatrium.getAxisRight().setAxisMaximum(4000f);
        barChartNatrium.getAxisLeft().setAxisMaximum(4000f);
        barChartNatrium.getAxisLeft().setEnabled(false);
        barChartNatrium.animateY(1100);
        barChartNatrium.getAxisRight().setLabelCount(9, true);
        barChartNatrium.getAxisLeft().setLabelCount(9,true);

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
        barChartCalcium.getAxisRight().setLabelCount(9, true);
        barChartCalcium.getAxisLeft().setLabelCount(9,true);

        barChartCalcium.getXAxis().setEnabled(false);

        barChartCalcium.getAxisRight().setDrawAxisLine(false);
        barChartCalcium.getLegend().setEnabled(false);
        barChartCalcium.getDescription().setEnabled(false);

        //no zoom
        barChartCalcium.setPinchZoom(false);
        barChartCalcium.setTouchEnabled(false);
        barChartCalcium.setDoubleTapToZoomEnabled(false);



    }

    private void setupPieChart(){

        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getLegend().setEnabled(false);
        pieChart.getDescription().setEnabled(true);
        pieChart.getDescription().setText("Recommended");
        pieChart.getDescription().setTextSize(16f);
        pieChart.setExtraBottomOffset(10f);
        pieChart.getDescription().setPosition(380f, 505f);

        pieChartUser.setDrawHoleEnabled(true);
        pieChartUser.setUsePercentValues(true);
        pieChartUser.setEntryLabelTextSize(12);
        pieChartUser.setEntryLabelColor(Color.BLACK);
        pieChartUser.getLegend().setEnabled(false);
        pieChartUser.getDescription().setEnabled(true);
        pieChartUser.getDescription().setText("Actual");
        pieChartUser.getDescription().setTextSize(16f);
        pieChartUser.setExtraBottomOffset(10f);
        pieChartUser.getDescription().setPosition(300f, 505f);
    }


    private void loadPieChart()
    {
        ArrayList<PieEntry> entries = new ArrayList<>();


        //value: daten die mitgegeben werden, um anzuzeigen wv der nährstoffe eingenommen wurdem
        entries.add(new PieEntry(0.55f, "Carbs"));
        entries.add(new PieEntry(0.2f, "Proteins"));
        entries.add(new PieEntry(0.25f, "Fats"));

        //colors liste wurde erstellt um die entries in farben einzukategorisieren.
        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: ColorTemplate.MATERIAL_COLORS)
        {
            colors.add(color);
        }

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
        entries.add(new PieEntry(0.2f, "Proteins"));
        entries.add(new PieEntry(0.25f, "Fats"));

        //colors liste wurde erstellt um die entries in farben einzukategorisieren.
        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: ColorTemplate.MATERIAL_COLORS)
        {
            colors.add(color);
        }

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);


        txtusername = (TextView) root.findViewById(R.id.home_username);

        if(username != null)
        {
            txtusername.setText(username);
        }
        else
        {
            txtusername.setText("Sascha Karottenmann");
        }



        barChartNatrium = root.findViewById(R.id.barchart_natrium);
        barChartCalcium = root.findViewById(R.id.barchart_calcium);
        barChartIron = root.findViewById(R.id.barchart_iron);
        barChartMagensium = root.findViewById(R.id.barchart_magnesium);
        barChartPhosphor = root.findViewById(R.id.barchart_phosphor);
        barChartPotassium = root.findViewById(R.id.barchart_potassium);


        pieChart = root.findViewById(R.id.piechart_standard);
        pieChartUser = root.findViewById(R.id.piechart_user);

        setBarChart();
        setupPieChart();
        loadBarChartPot();
        loadBarChartCal();
        loadBarChartIron();
        loadBarChartNat();
        loadBarChartMag();
        loadBarChartPho();

        loadPieChartUser();
        loadPieChart();

        return root;
    }
}