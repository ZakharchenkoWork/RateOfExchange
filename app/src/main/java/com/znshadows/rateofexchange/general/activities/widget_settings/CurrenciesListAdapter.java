package com.znshadows.rateofexchange.general.activities.widget_settings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.znshadows.rateofexchange.App;
import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;
import com.znshadows.rateofexchange.mvp.presenters.IBankRatesPresenter;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by Evolution on 3/9/17.
 */

public class CurrenciesListAdapter extends RecyclerView.Adapter<CurrenciesListAdapter.ViewHolder> {

    public interface OnCurrencyChoosenListener {
        void onChoose(String code);
    }

    private Context context;
    private List<UnifiedBankResponce> dataList;
    private OnCurrencyChoosenListener listener = (s) -> {
    };

    /**
     * @param listener to be invoked when user clicks on currency
     */
    public void setOnCurrencyChoosenListener(OnCurrencyChoosenListener listener) {
        this.listener = listener;
    }

    /**
     * ViewHolder class will contain row view for RecyclerView
     */


    public CurrenciesListAdapter(Context context, List<UnifiedBankResponce> dataList) {
        this.context = context;
        this.dataList = dataList;

    }


    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_rate_list, null);
        return new ViewHolder(view);

    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.code.setText(dataList.get(position).getCode());
        holder.rateBuy.setVisibility(View.GONE);
        holder.rateCheckBox.setVisibility(View.GONE);

        holder.itemView.setOnClickListener((v) -> {
            listener.onChoose(dataList.get(position).getCode());
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView code;
        TextView rateBuy;
        TextView rateSale;
        CheckBox rateCheckBox;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            code = (TextView) itemView.findViewById(R.id.code);
            rateBuy = (TextView) itemView.findViewById(R.id.rateBuy);
            rateSale = (TextView) itemView.findViewById(R.id.rateSale);
            rateCheckBox = (CheckBox) itemView.findViewById(R.id.rateCheckBox);
        }
    }
}
