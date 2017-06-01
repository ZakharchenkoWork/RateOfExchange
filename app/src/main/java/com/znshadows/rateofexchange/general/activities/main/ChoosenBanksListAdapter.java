package com.znshadows.rateofexchange.general.activities.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.znshadows.rateofexchange.R;
import com.znshadows.rateofexchange.general.models.BANKS;
import com.znshadows.rateofexchange.general.models.UnifiedBankResponce;

import java.util.List;

/**
 * Created by kostya on 30.05.2017.
 */

public class ChoosenBanksListAdapter extends RecyclerView.Adapter<ChoosenBanksListAdapter.ViewHolder> {
        private View.OnClickListener onItemClickListener = null;
        private Context context;
        private List<BANKS> dataList;

        /**
         * ViewHolder class will contain row view for RecyclerView
         */


        public ChoosenBanksListAdapter(Context context, List<BANKS> dataList) {
            this.context = context;
            this.dataList = dataList;
        }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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

            View view = LayoutInflater.from(context).inflate(R.layout.item_banks_list, null);

            return new ViewHolder(view);

        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.logo.setImageResource(context.getResources().obtainTypedArray(R.array.banks_logo).getResourceId(dataList.get(position).ordinal(), -1));
            holder.name.setText(context.getResources().getStringArray(R.array.bankNames)[dataList.get(position).ordinal()]);

            holder.code.setText("sdf");
            holder.rate.setText("sd");
            if(onItemClickListener != null){
                holder.itemView.setOnClickListener(onItemClickListener);
            }
        }

        // Returns the total count of items in the list
        @Override
        public int getItemCount() {
            return dataList.size();
        }


        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView logo;
            TextView name;
            TextView code;
            TextView rate;
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
                code = (TextView) itemView.findViewById(R.id.code);
                rate = (TextView) itemView.findViewById(R.id.rate);
            }
        }
    }

