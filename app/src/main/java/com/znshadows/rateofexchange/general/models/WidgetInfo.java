package com.znshadows.rateofexchange.general.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by kostya on 26.06.2017.
 */

@DatabaseTable(tableName = "widgets_data")
public class WidgetInfo {
    @DatabaseField(id = true)
    private int widgetId;
    @DatabaseField
    private BANKS choosenBank;
    @DatabaseField
    private String choosenCurrency;

    public WidgetInfo() {}

    public WidgetInfo(int widgetId, BANKS choosenBank, String choosenCurrency) {
        this.widgetId = widgetId;
        this.choosenBank = choosenBank;
        this.choosenCurrency = choosenCurrency;
    }

    public int getWidgetId() {
        return widgetId;
    }

    public BANKS getChoosenBank() {
        return choosenBank;
    }

    public String getChoosenCurrency() {
        return choosenCurrency;
    }

}
