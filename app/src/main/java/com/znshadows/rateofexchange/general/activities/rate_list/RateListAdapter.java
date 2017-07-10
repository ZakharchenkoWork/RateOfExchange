package com.znshadows.rateofexchange.general.activities.rate_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class RateListAdapter extends RecyclerView.Adapter<RateListAdapter.ViewHolder> {

    @Inject
    IBankRatesPresenter presenter;


    private List<String> choosenCurrencies;
    private BANKS bank;
    private Context context;
    private List<UnifiedBankResponce> dataList;

    /**
     * ViewHolder class will contain row view for RecyclerView
     */


    public RateListAdapter(Context context, BANKS bank, List<UnifiedBankResponce> dataList) {
        App.getAppComponent().inject(this);
        this.context = context;
        this.dataList = dataList;
        this.bank = bank;
        choosenCurrencies = presenter.getChoosenCurrencies(bank);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    enum VIEW_TYPE {
        HEADER,
        ITEM
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE.HEADER.ordinal() : VIEW_TYPE.ITEM.ordinal();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_rate_list, null);
        return new ViewHolder(view);

    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (getItemViewType(position) == VIEW_TYPE.HEADER.ordinal()) {
            if (dataList != null && dataList.size() > 0){
                 if(dataList.get(0).getSale() == UnifiedBankResponce.NO_VALUE){
                     handleSaleState(holder, dataList.get(0));
                 }
            }
            holder.rateCheckBox.setVisibility(View.INVISIBLE);
        } else {
            UnifiedBankResponce bankResponce = dataList.get(position - 1);

            holder.code.setText(bankResponce.getCode());
            holder.rateBuy.setText("" + bankResponce.getBuy());
            holder.rateCheckBox.setVisibility(View.VISIBLE);


            if (choosenCurrencies.contains(bankResponce.getCode())) {
                holder.rateCheckBox.setChecked(true);
            } else {
                holder.rateCheckBox.setChecked(false);
            }
            //used instead of setOnCheckedChangeListener because it's get called when ViewHolder is destroyed
            holder.rateCheckBox.setOnClickListener(checkBox -> {
                if (((CheckBox) checkBox).isChecked()) {
                    choosenCurrencies = presenter.addCurrency(bank, bankResponce.getCode());
                } else {
                    choosenCurrencies = presenter.removeCurrency(bank, bankResponce.getCode());
                }
            });
            handleSaleState(holder, bankResponce);
        }
    }

    private void handleSaleState(ViewHolder holder, UnifiedBankResponce bankResponce) {
        if (bankResponce.getSale() == UnifiedBankResponce.NO_VALUE) {
            holder.rateSale.setVisibility(View.GONE);
        } else {
            holder.rateSale.setText("" + bankResponce.getSale());
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return dataList.size() + 1;// One is for header
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
