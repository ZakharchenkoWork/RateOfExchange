package com.znshadows.rateofexchange.general.activities.rate_list;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {

        UnifiedBankResponce bankResponce = dataList.get(position);
        holder.code.setText(bankResponce.getCode());
        holder.rateBuy.setText("" + bankResponce.getBuy());
        holder.rateCheckBox.setVisibility(View.VISIBLE);

        boolean isFound = false;
        for (int i = 0; i < choosenCurrencies.size(); i++) {
            if (choosenCurrencies.get(i).equals(bankResponce.getCode())) {
                isFound = true;
            }
        }
        if (isFound) {
            holder.rateCheckBox.setChecked(true);
        } else {
            holder.rateCheckBox.setChecked(false);
        }
        holder.rateCheckBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                presenter.addCurrency(bank, bankResponce.getCode());
            } else {
                presenter.removeCurrency(bank, bankResponce.getCode());
            }
            choosenCurrencies = presenter.getChoosenCurrencies(bank);

        });
        if (bankResponce.getSale() == UnifiedBankResponce.NO_VALUE) {
            holder.rateSale.setVisibility(View.GONE);
        } else {
            holder.rateSale.setText("" + bankResponce.getSale());
        }
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
