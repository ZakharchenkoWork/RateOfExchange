package com.znshadows.exchangerate.general.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Natali Zakharchenko on 26.06.2017.
 */

@DatabaseTable(tableName = "widgets_data")
public class WidgetInfo {
    @DatabaseField(id = true)
    private int widgetId;
    @DatabaseField
    private BANKS chosenBank;
    @DatabaseField
    private String chosenCurrency;

    public WidgetInfo() {}

    public WidgetInfo(int widgetId, BANKS chosenBank, String chosenCurrency) {
        this.widgetId = widgetId;
        this.chosenBank = chosenBank;
        this.chosenCurrency = chosenCurrency;
    }

    public int getWidgetId() {
        return widgetId;
    }

    public BANKS getChoosenBank() {
        return chosenBank;
    }

    public String getChoosenCurrency() {
        return chosenCurrency;
    }

}
