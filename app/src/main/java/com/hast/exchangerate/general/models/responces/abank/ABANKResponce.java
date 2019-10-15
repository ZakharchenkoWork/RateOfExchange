
package com.hast.exchangerate.general.models.responces.abank;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ABANKResponce {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("processing")
    @Expose
    private List<Datum> processing = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public List<Datum> getProcessing() {
        return processing;
    }

    public void setProcessing(List<Datum> processing) {
        this.processing = processing;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
