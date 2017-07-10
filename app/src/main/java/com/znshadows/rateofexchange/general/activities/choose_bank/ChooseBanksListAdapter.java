package com.znshadows.rateofexchange.general.activities.choose_bank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.models.BANKS;

import java.util.List;

/**
 * Created by kostya on 30.05.2017.
 */

public class ChooseBanksListAdapter extends RecyclerView.Adapter<ChooseBanksListAdapter.ViewHolder> {

    private Context context;
    private List<BANKS> dataList;
    private List<BANKS> choosenBanksList;

    public ChooseBanksListAdapter(Context context, List<BANKS> dataList, List<BANKS> choosenBanksList) {
        this.context = context;
        this.dataList = dataList;
        this.choosenBanksList = choosenBanksList;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    // Called once, on first item
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_banks_list, null);
        return new ViewHolder(view);

    }

    // Called when adapter need a new view
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(context.getResources().getStringArray(R.array.bankNames)[dataList.get(position).ordinal()]);
        holder.logo.setImageResource(context.getResources().obtainTypedArray(R.array.banks_logo).getResourceId(dataList.get(position).ordinal(), -1));
        holder.bankCheckBox.setChecked(choosenBanksList.contains(dataList.get(position)));

        //used instead of setOnCheckedChangeListener because it's get called when ViewHolder is destroyed
        holder.bankCheckBox.setOnClickListener(checkBox -> {
            if (((CheckBox) checkBox).isChecked()) {
                choosenBanksList.add(dataList.get(position));
            } else {
                choosenBanksList.remove(dataList.get(position));
            }
        });
    }



    public List<BANKS> getCheckedList() {
        return choosenBanksList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView name;
        CheckBox bankCheckBox;
        View itemView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            this.itemView = itemView;
            logo = (ImageView) itemView.findViewById(R.id.logo);
            name = (TextView) itemView.findViewById(R.id.name);
            bankCheckBox = (CheckBox) itemView.findViewById(R.id.bankCheckBox);
        }
    }

}

