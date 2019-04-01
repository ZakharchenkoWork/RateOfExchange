package com.znshadows.exchangerate.general.activities.widget_settings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.znshadows.exchangerate.R;
import com.znshadows.exchangerate.general.models.BANKS;

import java.util.List;


/**
 * Created by Natali Zakharchenko on 24.06.2017.
 */

public class AllBanksListAdapter extends RecyclerView.Adapter<AllBanksListAdapter.ViewHolder> {
    public interface OnBankChosenListener {
        void onChoose(BANKS bank);
    }

    private OnBankChosenListener listener;

    public void setOnBankChosenListener(OnBankChosenListener listener) {
        this.listener = listener;
    }

    private Context context;
    private List<BANKS> dataList;

    /**
     * ViewHolder class will contain row view for RecyclerView
     */


    public AllBanksListAdapter(Context context, List<BANKS> dataList) {
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
    public AllBanksListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_all_banks_list, null);
        return new AllBanksListAdapter.ViewHolder(view);

    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(AllBanksListAdapter.ViewHolder holder, int position) {

        holder.name.setText(context.getResources().getStringArray(R.array.bankNames)[dataList.get(position).ordinal()]);
        holder.logo.setImageResource(context.getResources().obtainTypedArray(R.array.banks_logo).getResourceId(dataList.get(position).ordinal(), -1));
        holder.itemView.setOnClickListener((v) -> {
            if (listener != null) {
                listener.onChoose(dataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView name;
        View itemView;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            logo = itemView.findViewById(R.id.logo);
            name = itemView.findViewById(R.id.name);
            itemView.findViewById(R.id.bankCheckBox).setVisibility(View.GONE);
        }
    }
}
