package com.example.android.myfishy.ui.nutrition_alarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.android.myfishy.R;
import com.example.android.myfishy.db.entities.DietaryRestrictionTable;
import com.github.mikephil.charting.data.PieEntry;

public class NutritionAlarmFragment extends Fragment {

    private NutritionAlarmViewModel notificationsViewModel;

    private TextView condTitle;
    private TextView condText;
    private ImageView imgCond;

    private TextView salt;
    private TextView natrium;
    private TextView kalium;
    private TextView kalzium;
    private TextView phosphat;
    private TextView drinkforkimi;
    private TextView carbss;
    private TextView proteine;
    private TextView fett;
    private TextView energie;


    String condition_name;
    float table_salt;
    float potassium;
    float calcium;
    float phosphor;
    float sodium;
    String diet_name;
    float protein;
    int calories;
    float liquid_intake;
    float carbs;
    float fats;
    float fibers;

    private String morbus = "Morbus Crohn zählt wie die Colitis ulcerosa zu den chronisch entzündlichen Darmkrankheiten. Die Crohn-Krankheit kann prinzipiell in jedem Abschnitt des Verdauungstrakts auftauchen – vom Mund bis zum After. Meist ist jedoch das Ende des Dünndarms (Ileum) oder der obere Abschnitt des Dickdarms (Kolon) betroffen.";
    private String colitis = "Die Colitis ulcerosa ist eine vom Enddarm ausgehende chronisch-entzündliche Darmerkrankung. Sie ist durch einen entzündlichen Befall des Dickdarms bzw. des Colons gekennzeichnet. Im Gegensatz zum Morbus Crohn ist von der Entzündung nur der Dickdarm kontinuierlich betroffen und diese ist auf die Darmschleimhaut (Mukosa) und die darunter liegende Bindegewebsschicht (Submukosa) beschränkt.";
    private String diaTyp1 = "Diabetes mellitus Typ 1 ist eine Autoimmunerkrankung, bei der Betroffene kein oder kaum Insulin produzieren. Er entsteht, wenn das körpereigene Immunsystem, das in erster Linie der Abwehr krankmachender Keime dient, sich gegen die Insulin produzierenden Beta-Zellen der Bauchspeicheldrüse richtet und sie zerstört. In der Folge kommt es oft sehr schnell zum Ausbleiben der Insulinproduktion." ;
    private String diaTyp2 = "Der Typ-2-Diabetes ist eine chronische Stoffwechselkrankheit. Kennzeichnend für die Erkrankung ist ein erhöhter Zuckerspiegel im Blut. Der Grund dafür ist in der Regel eine Kombination aus erblicher Veranlagung, ungesunder Ernährung und Bewegungsmangel, die in der Folge zu einer Insulinresistenz führen. Insulinresistenz bedeutet, dass die Körperzellen schlechter auf das Hormon Insulin ansprechen. Insulin hat die Aufgabe, Zucker (Glukose) aus dem Blut in die Zellen zu schleusen, die ihn als Energiequelle benötigen. Bei einer Insulinresistenz gelingt das nur unzureichend. Der Zucker staut sich deshalb im Blut an.";
    private String CNI = "Bei der Niereninsuffizienz ist die Entgiftungsfunktion der Nieren eingeschränkt. Dies macht sich bei einer chronischen Nierenkrankheit zunächst wegen der funktionellen Überkapazität des Organs lange Zeit nicht bemerkbar. Bei einer akuten Niereninsuffizienz kann es dagegen rach zu Vergiftungserscheinungen mit Bewustseinseintrübung kommen. Oft ist eine Niereninsuffizienz mit hohem Blutdruck verbunden. Häufige Ursachen sind die Zuckerkrankheit und chronische Entzündungen.\n \n";
    private String CNI13a= "Im ersten Niereninsuffizienz-Stadium beträgt die GFR (glomuläre Filtrationsrate) noch mehr als 90 ml pro Minute. Der Kreatinin-Spiegel im Blut ist normal, allerdings findet sich oft schon vermehrt Eiweiß im Urin – eine Anzeichen für eine Störung der Nierenfunktion. Im Ultraschall zeigen sich manchmal schon erste krankhafte Veränderungen der Nieren. Eine Niereninsuffizienz Stadium 1 verursacht Betroffenen oft noch keine Beschwerden, es können aber bereits Wassereinlagerungen im Gewebe (Ödem) oder verfärbter Urin auftreten. In diesem frühen Stadium wird die Niereninsuffizienz aber meist nur zufällig entdeckt. Lässt sich die Ursache der Nierenschwäche herausfinden und behandeln, kann man damit einer weiteren Abnahme der Nierenfunktion entgegen wirken..Das Niereninsuffizienz-Stadium 2 ist durch eine GFR zwischen 60 und 89 Milliliter pro Minute gekennzeichnet. Nach wie vor zeigen sich im Blut aber in der Regel keine Auffälligkeiten. Nur eine gezielte Untersuchung der Nierenfunktion lässt die gestörte Filterleistung erkennen.";
    private String CNI3b4="Im Niereninsuffizienz-Stadium 3 beträgt die GFR (glomuläre Filtrationsrate) zwischen 30 und 59 Milliliter pro Minute. Die Filterfunktion der Nieren ist nun soweit verringert, dass die Blutwerte von Kreatinin und Harnstoff ansteigen. Spätestens jetzt kommt es zu Beschwerden: Bluthochdruck, Leistungsabfall und rasche Ermüdbarkeit treten auf. Die Wahrscheinlichkeit für Herz-Kreislauf-Erkrankungen nimmt deutlich zu. Nehmen die Patienten Medikamente, die normalerweise über die Nieren ausgeschieden werden, muss deren Dosis verringert werden, um Nebenwirkungen zu vermeiden. .Sinkt die GFR auf einen Wert zwischen 15 und 29 Milliliter pro Minute, sprechen Mediziner vom Niereninsuffizienz-Stadium 4. Die deutlich verringerte Nierenfunktion verursacht immer stärkere Beschwerden wie etwa Appetitlosigkeit, Übelkeit, Erbrechen, Müdigkeit, Juckreiz, Nerven- und Knochenschmerzen. Außerdem bilden sich vermehrt Ödeme, zum Beispiel an den Beinen oder um Gesicht." ;
    private String CNI5= "Das Niereninsuffizienz-Stadium 5 mit einer GFR (glomuläre Filtrationsrate) unter 15 Millilitern pro Minute wird auch terminale Niereninsuffizienz genannt, also Nierenschwäche im Endstadium. Die Nierenfunktion ist nun massiv eingeschränkt beziehungsweise die Nieren fallen völlig aus, das heißt sie können das Blut nicht mehr reinigen. Diese Aufgabe muss nun schnell ein Nierenersatzverfahren übernehmen, sonst vergiften die harnpflichtigen Substanzen den Körper: Der Patient ist auf eine Blutwäsche (Hämodialyse, HD), Bauchwäsche (Peritonealdialyse, PD) oder Nierentransplantation angewiesen.";
    DietaryRestrictionTable drt = new DietaryRestrictionTable(condition_name, diet_name, table_salt, sodium, potassium, calcium, phosphor, protein, calories, liquid_intake, carbs, fats, fibers);


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NutritionAlarmViewModel.class);
        View root = inflater.inflate(R.layout.fragment_nutrition_alarm, container, false);

        condition_name = drt.getCondition_name();

        condTitle = (TextView) root.findViewById(R.id.text_condition_title);
        condText = (TextView) root.findViewById(R.id.text_condition_text);
        imgCond = (ImageView) root.findViewById(R.id.imageView_condition);

         salt = (TextView) root.findViewById(R.id.text_salt);
         natrium = (TextView) root.findViewById(R.id.text_natrium);
         kalium = (TextView) root.findViewById(R.id.text_kalium);
         kalzium = (TextView) root.findViewById(R.id.text_kalzium);
         phosphat = (TextView) root.findViewById(R.id.text_phosphat);
         drinkforkimi = (TextView) root.findViewById(R.id.text_drink);
         carbss = (TextView) root.findViewById(R.id.text_kohlenhydrate);
         proteine = (TextView) root.findViewById(R.id.text_eiweiss);
         fett = (TextView) root.findViewById(R.id.text_fett);
         energie = (TextView) root.findViewById(R.id.text_energie);


        switch (condition_name)
        {
            case "CNI Stufe 1-3a" :
                condTitle.setText(condition_name);
                condText.setText(CNI + CNI13a);
                salt.setText("4-6 g");
                natrium.setText("<90-100 mmol");
                phosphat.setText("12 mg/kg");
                kalium.setText("> 4700 mg");
                energie.setText("25-35 kcal/kg");
                proteine.setText("0.8 g/kg");
                imgCond.setImageResource(R.drawable.image_green_ribbon);
                break;
            case "CNI Stufe 3b-4":
                condTitle.setText(condition_name);
                condText.setText(CNI + CNI3b4);
                salt.setText("4-6 g");
                natrium.setText("<90-100 mmol");
                phosphat.setText("12-15 mg/kg");
                kalium.setText("> 4700 mg");
                kalzium.setText("2 g");
                energie.setText("30-40 kcal/kg");
                proteine.setText("0.8-1.0 g/kg");

                imgCond.setImageResource(R.drawable.image_green_ribbon);
                break;
            case "CNI Stufe 5":
                condTitle.setText(condition_name);
                condText.setText(CNI + CNI5);
                imgCond.setImageResource(R.drawable.image_green_ribbon);
                salt.setText("4-6 g");
                natrium.setText("<90-100 mmol");
                phosphat.setText("15 mg/kg");
                kalium.setText("< 1500-2700 mg");
                kalzium.setText("2 g");
                energie.setText("30-40 kcal/kg");
                proteine.setText("0.8-1.0 g/kg");
                break;
            case "CNI Stufe 5D":
                condTitle.setText(condition_name);
                condText.setText(CNI + CNI5);
                imgCond.setImageResource(R.drawable.image_green_ribbon);
                salt.setText("4-6 g");
                natrium.setText("<90-100 mmol");
                phosphat.setText("17 mg/kg");
                kalium.setText("< 1500-2700 mg");
                kalzium.setText("2 g");
                energie.setText("30-40 kcal/kg");
                proteine.setText("> 1.1 g/kg");
                drinkforkimi.setText("500-800 ml");
                break;
            case "Typ 1":
                condTitle.setText("Diabetes " + condition_name);
                condText.setText(diaTyp1);
                imgCond.setImageResource(R.drawable.image_diabetes_symbol);
                carbss.setText("55% vom Tagesbedarf");
                proteine.setText("17% vom Tagesbedarf");
                fett.setText("28% vom Tagesbedarf");
                break;
            case "Typ 2":
                condTitle.setText("Diabetes " + condition_name);
                condText.setText(diaTyp2);
                imgCond.setImageResource(R.drawable.image_diabetes_symbol);
                carbss.setText("55% vom Tagesbedarf");
                proteine.setText("17% vom Tagesbedarf");
                fett.setText("28% vom Tagesbedarf");
                break;
            case "Morbus Crohn":
                condTitle.setText(condition_name);
                condText.setText(morbus);
                imgCond.setImageResource(R.drawable.image_violete_ribbon);
                carbss.setText("485% vom Tagesbedarf");
                proteine.setText("17% vom Tagesbedarf");
                fett.setText("35% vom Tagesbedarf");
                break;
            case "Colitis Ulc.":
                condTitle.setText(condition_name);
                condText.setText(colitis);
                imgCond.setImageResource(R.drawable.image_violete_ribbon);
                carbss.setText("485% vom Tagesbedarf");
                proteine.setText("17% vom Tagesbedarf");
                fett.setText("35% vom Tagesbedarf");
                break;
            default:
                condTitle.setText("Keine Erkrankung");
                condText.setText("Sie haben kein Krankheitsbild ausgewählt");

        }





        return root;
    }
}