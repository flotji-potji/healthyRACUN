package com.example.android.myfishy.utilities;

// MALE :BMR (Basal Metabolic Rate) = (height in centimeters x 6.25) + (weight in kilograms x 9.99) – (age x 4.92) + 5.
// FEMALE :BMR (Basal Metabolic Rate) = (height in centimetres x 6.25) + (weight in kilograms x 9.99) – (age x 4.92) – 161.
// MALE : (sedentary) TDEE (Total Daily Energy Expenditure) = BMR * 1.1
// FEMALE : (sedentary) TDEE (Total Daily Energy Expenditure) = BMR * 1.2

// Gram per Calorie per Macronutrient --> 1g Carb - 4 kcal; 1g Fat - 9 kcal; 1g Protein - 4 kcal;

//Diabetes:
// Macro Split Diabetes Melitus --> Crb: 45-65%; Fts: 20-35% Prtn: 10-35% (>> C55;F28;P17) (https://www.straighthealthcare.com/diabetic-diet.html)

//Chronische Darmentzündungen
//Macro Split CED/IBS: --> Crb: 48%; Fts: 35%; Prtn: 17% (den Split hab ich mir in Kombination mit folgenem Journal bissi aus der Nase gezogen >> https://doi.org/10.3892/mmr.2013.1565)

public class CalorieRechner {
    public static void main(String[] args) {
        CalorieRechner test = new CalorieRechner(83,178,25,"männlich", "Diabetes Typ 1");

    }

    private int groesse;
    private int gewicht;
    private int alter;
    private String geschlecht;
    private String erkrankung;
    private int BMR;
    private int TDEE;
    private int calCarb;
    private int calFat;
    private int calProt;
    private int gCarb;
    private int gProt;
    private int gFat;

    public CalorieRechner(int gewicht, int groesse, int alter, String geschlecht, String erkrankung){
        this.gewicht = gewicht;
        this.groesse = groesse;
        this.alter = alter;
        this.geschlecht = geschlecht;


        if (geschlecht.equals("männlich")){
            int BMR = (int) ((groesse * 6.25) + (gewicht * 9.99) - (alter * 4.92) + 5);
            TDEE = (int) (BMR * 1.1);
        } else {
            int BMR = (int) ((groesse * 6.25) + (gewicht * 9.99) - (alter * 4.92) - 116);
            TDEE = (int) (BMR * 1.2);
        }
        if (erkrankung.equals("Diabetes Typ 1" || "Diabetes Typ 2")){
            MacroSplitDiabetes();
        } else if (erkrankung.equals("Morbus Crohn" || "Colitis Ulcerosa")){
            MacroSplitCED();
        }

        CalorieConversion();

    }

        private void MacroSplitDiabetes() {
            calCarb = (int) (TDEE * 0.55);
            calFat = (int) (TDEE * 0.28);
            calProt = (int) (TDEE * 0.17);
        }

        private void MacroSplitCED() {
            calCarb = (int) (TDEE * 0.48);
            calFat = (int) (TDEE * 0.35);
            calProt = (int) (TDEE * 0.17);
        }

        private void CalorieConversion() {
        gCarb = calCarb / 4;
        gFat = calFat / 9;
        gProt = calProt / 4;
        System.out.printf("Tägliche Kalorien bei %d, davon %d g Kohlehydrate, %d g Fette, %d g Proteine", TDEE,gCarb,gFat,gProt);
    }


 /*   public int getGroesse() {
        return groesse;
    }

    public void setGroesse(int groesse) {
        this.groesse = groesse;
        MacroSplit();
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
        MacroSplit();
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
        MacroSplit();
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
        MacroSplit();
    }

    public int getBMR() {
        return BMR;
    }

//    BMR Setter ist unnötig; wenn man schon was settet, dann am ehesten gleich die TDEE
//    public void setBMR(int BMR) {
//        this.BMR = BMR;
//    }

    public int getTDEE() {
        return TDEE;
    }

    public void setTDEE(int TDEE) {
        this.TDEE = TDEE;
        MacroSplit();
    }
*/
}