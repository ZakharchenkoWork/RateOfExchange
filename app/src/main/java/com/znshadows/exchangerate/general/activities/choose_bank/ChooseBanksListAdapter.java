package com.znshadows.exchangerate.general.activities.choose_bank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.znshadows.exchangerate.R;
import com.znshadows.exchangerate.general.models.BANKS;

import java.util.List;

/**
 * Created by Natali Zakharchenko on 30.05.2017.
 */

public class ChooseBanksListAdapter extends RecyclerView.Adapter<ChooseBanksListAdapter.ViewHolder> {

    private Context context;
    private List<BANKS> dataList;
    private List<BANKS> chosenBanksList;

    public ChooseBanksListAdapter(Context context, List<BANKS> dataList, List<BANKS> chosenBanksList) {
        this.context = context;
        this.dataList = dataList;
        this.chosenBanksList = chosenBanksList;
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
        holder.bankCheckBox.setChecked(chosenBanksList.contains(dataList.get(position)));

        //used instead of setOnCheckedChangeListener because it's get called when ViewHolder is destroyed
        holder.bankCheckBox.setOnClickListener(checkBox -> {
            if (((CheckBox) checkBox).isChecked()) {
                chosenBanksList.add(dataList.get(position));
            } else {
                chosenBanksList.remove(dataList.get(position));
            }
        });
    }


    List<BANKS> getCheckedList() {
        return chosenBanksList;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView name;
        CheckBox bankCheckBox;
        View itemView;


        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            logo = itemView.findViewById(R.id.logo);
            name = itemView.findViewById(R.id.name);
            bankCheckBox = itemView.findViewById(R.id.bankCheckBox);
        }
    }

}

