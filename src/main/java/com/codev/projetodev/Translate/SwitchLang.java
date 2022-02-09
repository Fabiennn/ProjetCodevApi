package com.codev.projetodev.Translate;

public enum SwitchLang {

    Energy("Energie"), Industrial_Processes("Processus industriels"), Land_Use_Change_and_Forestry("Changement d’affectation des terres et foresterie"),
    Bunker_Fuels("Soutes de Fiouls"), Electricity_Heat("Electricite et Chaleur"), Manufacturing_Construction("Fabrication et construction"),
    Transportation("Transport"), Building("Construction"),Other_Fuel_Combustion("Autre combustible"), Total_including_LUCF("Total incluant LUCF");

    private String translation;

    SwitchLang(String translation) {
        this.translation = translation;
    }

    public String getTranslate() {
        return this.translation;
    }
}
